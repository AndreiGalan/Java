package org.example.Compulsory.repository;

import org.example.Compulsory.manager.PersistenceManager;
import org.example.Compulsory.models.ArtistJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;


public class ArtistRepository {
    private final EntityManager entityManager;

    public ArtistRepository(EntityManagerFactory emf) {
        entityManager = PersistenceManager.getInstance().getArtistEntityManagerFactory().createEntityManager();
    }

    public void create(ArtistJPA artist) {
        entityManager.getTransaction().begin();
        entityManager.persist(artist);
        entityManager.getTransaction().commit();
    }

    public ArtistJPA findById(int id) {
        return entityManager.find(ArtistJPA.class, id);
    }

    public List<ArtistJPA> findByName(String namePattern) {
        TypedQuery<ArtistJPA> query = entityManager.createNamedQuery("ArtistJPA.findByName", ArtistJPA.class);
        query.setParameter("name", "%" + namePattern + "%");
        return query.getResultList();
    }

    public List<ArtistJPA> findAll() {
        TypedQuery<ArtistJPA> query = entityManager.createNamedQuery("ArtistJPA.findAll", ArtistJPA.class);
        return query.getResultList();
    }

    public void close()
    {
        entityManager.close();
    }
}
