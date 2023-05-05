package org.example.Compulsory;

import org.example.Compulsory.repository.AlbumRepository;
import org.example.Compulsory.repository.ArtistRepository;
import org.example.Compulsory.repository.GenreRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArtistJPA");
        EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("GenreJPA");
        EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("AlbumJPA");

        ArtistRepository artistRepository = new ArtistRepository(emf);
        GenreRepository genreRepository = new GenreRepository(emf2);
        AlbumRepository albumRepository = new AlbumRepository(emf3);

        System.out.println(artistRepository.findByName("AC"));

        System.out.println(artistRepository.findAll());

        System.out.println(artistRepository.findById(53));

        System.out.println(genreRepository.findByName("Rock"));

        System.out.println(genreRepository.findById(1));

        System.out.println(albumRepository.findByName("The Wall"));

        System.out.println(albumRepository.findByArtist("Pink Floyd"));

        System.out.println(albumRepository.findByGenre("Pop"));

        artistRepository.close();
    }
}