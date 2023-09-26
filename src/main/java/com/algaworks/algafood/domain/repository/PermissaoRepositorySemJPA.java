package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepositorySemJPA {

    List<Permissao> listar();
    Permissao buscar(Long id);
    Permissao salvar(Permissao cozinha);
    void remover(Permissao cozinha);

}
