package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepositorySemJPA;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaPermissaoMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PermissaoRepositorySemJPA permissaoRepositoy = applicationContext.getBean(PermissaoRepositorySemJPA.class);
        for (Permissao permissao : permissaoRepositoy.listar()) {
            System.out.printf("%s - %s\n", permissao.getNome(), permissao.getDescricao());
        }
    }
}
