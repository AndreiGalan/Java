package org.example.Compulsory.manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
    private static final PersistenceManager INSTANCE = new PersistenceManager();
    private final EntityManagerFactory artistEmf;
    private final EntityManagerFactory genreEmf;
    private final EntityManagerFactory albumEmf;

    private PersistenceManager() {
        artistEmf = Persistence.createEntityManagerFactory("ArtistJPA");
        genreEmf = Persistence.createEntityManagerFactory("GenreJPA");
        albumEmf = Persistence.createEntityManagerFactory("AlbumJPA");
    }

    public static PersistenceManager getInstance() {
        return INSTANCE;
    }

    public EntityManagerFactory getArtistEntityManagerFactory() {
        return artistEmf;
    }

    public EntityManagerFactory getGenreEntityManagerFactory() {
        return genreEmf;
    }

    public EntityManagerFactory getAlbumEntityManagerFactory() {
        return albumEmf;
    }

    public void closeEntityManagerFactories() {
        if (artistEmf != null && artistEmf.isOpen()) {
            artistEmf.close();
        }
        if (genreEmf != null && genreEmf.isOpen()) {
            genreEmf.close();
        }
        if (albumEmf != null && albumEmf.isOpen()) {
            albumEmf.close();
        }
    }
}






