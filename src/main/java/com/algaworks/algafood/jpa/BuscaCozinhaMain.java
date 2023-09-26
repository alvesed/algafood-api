package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

public class BuscaCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        // create a Optional
        Optional<Integer> op = Optional.of(9455);

        // print value
        System.out.println("****************************************");
        System.out.println("Optional: " + op);

        // apply ifPresentOrElse
        op.ifPresentOrElse((value) -> { System.out.println("Value is present, its: " + value); },
                () -> { System.out.println("Value is empty"); });


        CozinhaRepository cozinhaRepositoy = applicationContext.getBean(CozinhaRepository.class);
        Optional<Cozinha> cozinha = cozinhaRepositoy.findById(1L);
        System.out.println(cozinha.get().getNome());

    }
}
