package com.algaworks.algafood.jpa;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.swing.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TestesMain {
    public static void main(String[] args) {

        //testeFuncaoLambda();

        //testeFuncaoLambda2();

        //testeFuncaoLambdaStream();

        //testeStreamOperacoesIntermediarias();
        //testeStreamOperacoesTerminais();

//        testeMono();
//        testeFlux();

        testeMono2();

    }

    static void testeFuncaoLambda() {
        ArrayList<Integer> valores = new ArrayList<Integer>();
        ArrayList<Integer> dobro = new ArrayList<Integer>();
        ArrayList<Integer> par = new ArrayList<Integer>();
        ArrayList<Integer> impar = new ArrayList<Integer>();

        valores.add(1);
        valores.add(2);
        valores.add(3);
        valores.add(4);
        valores.add(5);
        valores.add(6);

//        Consumer<Integer> dobrar = (v) -> {dobro.add(v*2);};
//        valores.forEach(dobrar);
        valores.forEach((v) -> {
            dobro.add(v*2);
            if (v%2 == 0) {
                par.add(v);
            } else {
                impar.add(v);
            }
        });

        System.out.println(valores);
        System.out.println(dobro);
        System.out.println(par);
        System.out.println(impar);
    }

    static void testeFuncaoLambda2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Ola mundo!");
            }
        }).run();

        new Thread(() -> System.out.println("Olá mundo!")).run();

    }

    static void testeFuncaoLambda3() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Ola mundo!");
            }
        }).run();

        new Thread(() -> System.out.println("Olá mundo!")).run();

    }

    static void testeFuncaoLambdaStream() {
        List<Integer> asList = Arrays.asList(1,2,3,4);
        asList.stream()
                .filter(e -> e % 2 == 0)
                .forEach(e -> System.out.println(e));
    }

    static void testeStreamOperacoesIntermediarias() {
        List<Integer> lista = Arrays.asList(1,5,8,9,1,4,7,6,6,9,9);
//        for (Integer integer : lista) {
//            System.out.println(integer);
//        }

        //JAVA 8 - STREAM = FLUXO DE DADOS
        lista.stream()
                //operacoes intermediarias
                //.skip(2)
                //.limit(2)
                //.distinct() //precisa ter equals e hashcode implementados
                .filter(e -> e % 2 == 0)
                .forEach(e -> System.out.println(e));

        //stream com map - transformar o item do stream (lista original nao e modificada)
        System.out.println("********************");
        lista.stream()
                //.filter() //ideal e filtrar antes de transformar o dado
                .map(e -> e *2)
                .forEach(e -> System.out.println(e));


        System.out.println("Lista original = " + lista);
        System.out.println("********************");

    }

    static void testeStreamOperacoesTerminais() {
        List<Integer> lista = Arrays.asList(1, 5, 8, 9, 1, 4, 7, 6, 6, 9, 9);

        long count = lista.stream()
                .filter(e -> e % 2 == 0)
                .count();

        System.out.println(count);

        Optional<Integer> minimo = lista.stream()
                .filter(e -> e % 2 == 0)
                .min(Comparator.naturalOrder());

        System.out.println(minimo.get());

        System.out.println("Lista original = " + lista);
        System.out.println("********************");

        List<Integer> novaLista = lista.stream()
                .filter(e -> e % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Lista original = " + lista);
        System.out.println("Nova lista = " + novaLista);
        System.out.println("********************");

        Map<Boolean, List<Integer>> mapa = lista.stream()
                .map(e -> e * 3)
                .collect(Collectors.groupingBy(e -> e % 2 == 0));

        System.out.println("Lista original = " + lista);
        System.out.println("Mapa = " + mapa);
        System.out.println("********************");

        Map<Integer, List<Integer>> mapaRestoDivisao = lista.stream()
                .collect(Collectors.groupingBy(e -> e % 3));

        System.out.println("Lista original = " + lista);
        System.out.println("Mapa resto divisão = " + mapaRestoDivisao);
        System.out.println("********************");

        String collect = lista.stream()
                .map(e -> String.valueOf(e))
                .collect(Collectors.joining(";"));

        System.out.println("Lista original = " + lista);
        System.out.println("Lista de string = " + collect);
        System.out.println("********************");

    }

    static void testeMono() {
        System.out.println("********************");
        System.out.println("*     testeMono    *");
        System.out.println("********************");

        Mono<String> monoString = Mono.just("Edson").log();
        monoString.subscribe(System.out::println);

        Mono<?> monoStringError = Mono.just("Edson")
                .then(Mono.error(new RuntimeException("Ocorreu uma exceção")))
                .log();
        monoStringError.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));

    }

    static void testeFlux() {
        System.out.println("********************");
        System.out.println("*     testeFlux    *");
        System.out.println("********************");

        Flux<String> fluxSting = Flux.just("Spring", "Spring Boot", "Hibernate", "Microservice")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Ocorreu uma exceção")))
                .concatWithValues("Cloud")
                .log();
        fluxSting.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));


    }

    static void testeMono2() {
        System.out.println("********************");
        System.out.println("*     testeMono 2  *");
        System.out.println("********************");

//        var flux = monoList().flatMapMany(iterable -> Flux.fromIterable(iterable))
//                .subscribe(System.out::println);

        var flux = monoList()
                    .flatMapMany(Flux::fromIterable)
                    .log();
        List<String> array = flux.collectList().block();
        System.out.println(array);
        array.forEach(System.out::println);
    }




    private static Mono<List<String>> monoList() {
        String[] arrayStr = new String[] {"John", "Mike", "Jack", "Kevin", "Chris"};
        return Mono.just(Arrays.asList(arrayStr));
    }

}