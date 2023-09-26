package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cidade;

import java.util.List;

public interface CidadeRepositorySemJPA {

    List<Cidade> listar();
    Cidade buscar(Long id);
    Cidade salvar(Cidade cozinha);
    void remover(Long cidadeId);

}
