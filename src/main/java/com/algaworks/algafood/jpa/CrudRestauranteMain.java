package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepositorySemJPA;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Optional;

public class CrudRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        //LISTAR
        RestauranteRepositorySemJPA restauranteRepository = applicationContext.getBean(RestauranteRepositorySemJPA.class);
        for (Restaurante restaurante : restauranteRepository.listar()) {
            System.out.println(restaurante.getNome() + "/" + restaurante.getTaxaFrete());
        }

        //INCLUIR
        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome("Da Licença");
        restaurante1.setTaxaFrete(BigDecimal.valueOf(15.12));

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
        Optional<Cozinha> cozinha = cozinhaRepository.findById(2L);
        System.out.printf("%d - %s\n", cozinha.get().getId(), cozinha.get().getNome());
        restaurante1.setCozinha(cozinha.get());

        restaurante1 = restauranteRepository.salvar(restaurante1);

        System.out.printf("%d - %s\n", restaurante1.getId(), restaurante1.getNome());

        //ALTERAR
        Restaurante restaurante2 = new Restaurante();
        restaurante2.setNome("New Da Licença");
        restaurante2.setTaxaFrete(BigDecimal.valueOf(09.90));
        restaurante2.setId(4L);
        Cozinha cozinha2 = new Cozinha();
        cozinha2.setId(1L);
        restaurante2.setCozinha(cozinha2);

        restaurante2 = restauranteRepository.salvar(restaurante2);

        System.out.printf("%d - %s - %f\n", restaurante2.getId(), restaurante2.getNome(), restaurante2.getTaxaFrete());


        //BUSCAR
        Restaurante restaurante3 = restauranteRepository.buscar(1L);

        System.out.printf("%d - %s - %f\n", restaurante3.getId(), restaurante3.getNome(), restaurante3.getTaxaFrete());

        //REMOVER
//        Restaurante restaurante4 = new Restaurante();
//        restaurante4.setId(2L);
//
//        restauranteRepository.remover(restaurante4);
//
//        System.out.printf("%d - REMOVIDO\n", restaurante4.getId());

        //LISTAR RESTAURANTE COM SUAS RESPECTIVAS COZINHAS
        for (Restaurante restaurante : restauranteRepository.listar()) {
            System.out.println(restaurante.getNome() + "/" + restaurante.getTaxaFrete()
                                                    + "/" + restaurante.getCozinha().getNome());
        }
    }
}
