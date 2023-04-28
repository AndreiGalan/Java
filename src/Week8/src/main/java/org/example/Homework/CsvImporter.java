package org.example.Homework;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.example.Compulsory.Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

public class CsvImporter {
    private final MusicAlbumDAO musicAlbumDAO;
    private final ArtistDAO artistDAO;
    private final GenreDAO genreDAO;

    public CsvImporter(MusicAlbumDAO musicAlbumDAO, ArtistDAO artistDAO, GenreDAO genreDAO) {
        this.musicAlbumDAO = musicAlbumDAO;
        this.artistDAO = artistDAO;
        this.genreDAO = genreDAO;
    }

    public void importCsv(String filePath) throws IOException, SQLException {
        Connection connection = null;
        try {
            connection = Database.getConnection();

            try (Reader reader = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
                 CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {

                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    MusicAlbum musicAlbum = new MusicAlbum();
                    Artist artist = new Artist();
                    Genre genre = new Genre();
                    musicAlbum.setNumber(Integer.parseInt(line[0]));
                    musicAlbum.setYear(Integer.parseInt(line[1]));
                    musicAlbum.setAlbum(line[2]);
                    artist.setName(line[3]);
                    musicAlbum.setArtist(line[3]);
                    String[] genres = line[4].split("[/,]");
                    for (String genreSep : genres) {
                        if(genreSep.charAt(0) == ' ')
                            genreSep = genreSep.substring(1);
                        genre.setName(genreSep);
                    }
                    musicAlbum.setGenre(line[4]);
                    musicAlbum.setSubgenre(line[5]);
                    //System.out.println(musicAlbum);
                    artistDAO.create(artist);
                    genreDAO.create(genre);
                    musicAlbumDAO.create(musicAlbum);

                }
            } catch (CsvValidationException e) {
                throw new RuntimeException(e);
            }

            connection.commit();
        } catch (SQLException ex) {
            if (connection != null) {
                connection.rollback();
            }
            throw ex;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
