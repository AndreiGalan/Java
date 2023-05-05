package org.example.Compulsory.test;

import org.example.Compulsory.models.GenreJPA;
import org.example.Compulsory.repository.GenreRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenreRepositoryTest {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("GenreJPA");
    private static GenreRepository genreRepository;

    @BeforeAll
    public static void setup() {
        genreRepository = new GenreRepository(emf);
    }

    @AfterAll
    public static void tearDown() {
        genreRepository.close();
    }

    @Test
    public void testCreateGenre() {
        GenreJPA genre = new GenreJPA("Test Genre1");
        genreRepository.create(genre);
        GenreJPA genre2 = genreRepository.findById(genre.getId());
        assertEquals(genre, genre2);
    }

    @Test
    public void testFindGenretById() {
        GenreJPA genre1 = new GenreJPA("Test Genre2");
        genreRepository.create(genre1);
        GenreJPA genre2 = new GenreJPA("Test Genre3");
        genreRepository.create(genre2);
        GenreJPA persistedGenre = genreRepository.findById(genre1.getId());
        GenreJPA persistedGenre2 = genreRepository.findById(genre2.getId());
        assertEquals(genre1, persistedGenre);
        assertEquals(genre2, persistedGenre2);
    }

    @Test
    public void testFindGenreByName() {
        GenreJPA genre1 = new GenreJPA("Trap");
        GenreJPA genre2 = new GenreJPA("Trap romanesc");
        GenreJPA genre3 = new GenreJPA("Trap USA");

        genreRepository.create(genre1);
        genreRepository.create(genre2);
        genreRepository.create(genre3);



        List<GenreJPA> genres = genreRepository.findByName("Trap");
        assertEquals(3, genres.size());
        for(GenreJPA genre : genres) {
            assertEquals("Trap", genre.getName().substring(0, 4));
        }
    }
}
