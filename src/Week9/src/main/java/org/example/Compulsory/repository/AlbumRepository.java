package org.example.Compulsory.repository;

import org.example.Compulsory.manager.PersistenceManager;
import org.example.Compulsory.models.AlbumJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;


public class AlbumRepository {
    private final EntityManager entityManager;

    public AlbumRepository(EntityManagerFactory emf) {
        entityManager = PersistenceManager.getInstance().getAlbumEntityManagerFactory().createEntityManager();
    }

    public void create(AlbumJPA album) {
        entityManager.getTransaction().begin();
        entityManager.persist(album);
        entityManager.getTransaction().commit();
    }

    public AlbumJPA findById(int id) {
        return entityManager.find(AlbumJPA.class, id);
    }

    public List<AlbumJPA> findByName(String namePattern) {
        TypedQuery<AlbumJPA> query = entityManager.createNamedQuery("AlbumJPA.findByName", AlbumJPA.class);
        query.setParameter("title", "%" + namePattern + "%");
        return query.getResultList();
    }

    public List<AlbumJPA> findByArtist(String artistName) {
        //ArtistJPA artist = new ArtistJPA(artistName);
        TypedQuery<AlbumJPA> query = entityManager.createNamedQuery("AlbumJPA.findByArtist", AlbumJPA.class);
        query.setParameter("artist", artistName);
        return query.getResultList();
    }

    public List<AlbumJPA> findByGenre(String genreName) {
        TypedQuery<AlbumJPA> query = entityManager.createNamedQuery("AlbumJPA.findByGenre", AlbumJPA.class);
        query.setParameter("genre", genreName);
        return query.getResultList();
    }

    public List<AlbumJPA> findAll() {
        TypedQuery<AlbumJPA> query = entityManager.createNamedQuery("AlbumJPA.findAll", AlbumJPA.class);
        return query.getResultList();
    }

    public void close()
    {
        entityManager.close();
    }
}
