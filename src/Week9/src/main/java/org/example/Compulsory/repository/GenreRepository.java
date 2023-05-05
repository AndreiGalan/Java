package org.example.Compulsory.repository;

import org.example.Compulsory.manager.PersistenceManager;
import org.example.Compulsory.models.GenreJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;


public class GenreRepository{
    private final EntityManager entityManager;

    public GenreRepository(EntityManagerFactory emf) {
        entityManager = PersistenceManager.getInstance().getGenreEntityManagerFactory().createEntityManager();
    }

    public void create(GenreJPA genre) {
        entityManager.getTransaction().begin();
        entityManager.persist(genre);
        entityManager.getTransaction().commit();
    }

    public GenreJPA findById(int id) {
        return entityManager.find(GenreJPA.class, id);
    }

    public List<GenreJPA> findByName(String namePattern) {
        TypedQuery<GenreJPA> query = entityManager.createNamedQuery("GenreJPA.findByName", GenreJPA.class);
        query.setParameter("name", "%" + namePattern + "%");
        return query.getResultList();
    }

    public List<GenreJPA> findAll() {
        TypedQuery<GenreJPA> query = entityManager.createNamedQuery("GenreJPA.findAll", GenreJPA.class);
        return query.getResultList();
    }

    public void close()
    {
        entityManager.close();
    }
}
