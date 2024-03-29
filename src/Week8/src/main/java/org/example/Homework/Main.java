package org.example.Homework;

import org.example.Homework.DAO.ArtistDAOImpl;
import org.example.Homework.DAO.GenreDAOImpl;
import org.example.Homework.DAO.MusicAlbumDAOImpl;
import org.example.Homework.Database.Database;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/week8_homework";
        String username = "postgres";
        String password = "123456";
        String csvFilePath = "C:\\Users\\Andrei\\IdeaProjects\\Week8\\src\\main\\java\\org\\example\\Homework\\Database\\albumlist.csv";

        Database database = new Database(jdbcUrl, username, password);
        CsvImporter importer = new CsvImporter(new MusicAlbumDAOImpl(database), new ArtistDAOImpl(database), new GenreDAOImpl(database));;

        try {
            importer.importCsv(csvFilePath);
            System.out.println("Data imported successfully!");
            MusicAlbumDAOImpl albumDAO = new MusicAlbumDAOImpl(database);
            albumDAO.getAll();
            albumDAO.findById(234);
            albumDAO.findByYear(1971);
            albumDAO.findByArtist("The Beatles");
            albumDAO.findByGenre("Rock");
            System.out.println(albumDAO.getAll());

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            database.close();
        }
    }
}
