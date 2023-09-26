package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepositorySemJPA;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaFormaPagamentoMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        FormaPagamentoRepositorySemJPA formaPagamentoRepositoy = applicationContext.getBean(FormaPagamentoRepositorySemJPA.class);
        for (FormaPagamento formaPagamento : formaPagamentoRepositoy.listar()) {
            System.out.println(formaPagamento.getDescricao());
        }
    }
}
