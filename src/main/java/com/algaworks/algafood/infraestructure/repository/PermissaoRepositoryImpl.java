package com.algaworks.algafood.infraestructure.repository;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepositorySemJPA;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepositorySemJPA {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permissao> listar() {
        //return manager.createQuery("from Permissao", Permissao.class).getResultList();
        TypedQuery<Permissao> query = manager.createQuery("from Permissao", Permissao.class);
        return query.getResultList();
    }

    @Override
    public Permissao buscar(Long id) {
        return manager.find(Permissao.class, id);
    }

    @Transactional
    @Override
    public Permissao salvar(Permissao permissao) {
        return manager.merge(permissao);
    }

    @Transactional
    @Override
    public void remover(Permissao permissao) {
        permissao = buscar(permissao.getId());
        manager.remove(permissao);
    }

}
