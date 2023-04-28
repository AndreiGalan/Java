package org.example.Compulsory;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]) throws SQLException {
        try {
            var artists = new ArtistDAO();
            artists.create("Pink Floyd");
            artists.create("Michael Jackson");

            var genres = new GenreDAO();
            genres.create("Rock");
            genres.create("Funk");
            genres.create("Soul");
            genres.create("Pop");//TODO: Funk, Soul, Pop

            Database.getConnection().commit();

            var albums = new AlbumDAO();
            albums.create(1979, "The Wall", "Pink Floyd", "Rock");
            albums.create(1982, "Thriller", "Michael Jackson", "Funk,Soul,Pop");

            Database.getConnection().commit();

            //TODO: print all the albums in the database
            var allAlbums = albums.findAll();
            for (Album album : allAlbums) {
                System.out.println(album);
            }

            Database.getConnection().close();
        } catch (SQLException e) {
            Database.rollback();
            System.err.println(e);
        }
    }
}


