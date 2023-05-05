package org.example.Bonus.DAO;

import org.example.Bonus.Database.Database;
import org.example.Bonus.Entities.MusicAlbum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusicAlbumDAOImpl implements MusicAlbumDAO {
    private final Database connectionPool;

    public MusicAlbumDAOImpl(Database connectionPool) {
        this.connectionPool = connectionPool;
    }
    @Override
    public void create(MusicAlbum musicAlbum) throws SQLException {
        String sql = "INSERT INTO music_albums (number, year, album, artist, subgenre) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Check if a record with the same "number" value already exists in the table
            String checkSql = "SELECT COUNT(*) FROM music_albums WHERE number = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
                checkStatement.setInt(1, musicAlbum.getNumber());
                ResultSet resultSet = checkStatement.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);
                if (count > 0) {
                    //System.out.println("Record with number " + musicAlbum.getNumber() + " already exists.");
                    return;
                }
            }
            int artistId = findArtistByName(musicAlbum.getArtist());
            statement.setInt(1, musicAlbum.getNumber());
            statement.setInt(2, musicAlbum.getYear());
            statement.setString(3, musicAlbum.getAlbum());
            statement.setInt(4, artistId);
            statement.setString(5, musicAlbum.getSubgenre());
            statement.executeUpdate();
            connection.commit();
            System.out.println("Record with number " + musicAlbum.getNumber() + " was inserted.");
            int albumId = musicAlbum.getNumber();
            String[] genres = musicAlbum.getGenre().split("[/,]");
            for (String genre : genres) {
                int genreId = findGenreByName(genre.trim());
                if (genreId != 0) {
                        try (PreparedStatement pstmt = connection.prepareStatement(
                                "insert into album_genres (id_album, id_genre) values (?, ?)")) {
                            pstmt.setInt(1, albumId);
                            pstmt.setInt(2, genreId);
                            pstmt.executeUpdate();
                            connection.commit();
                            System.out.println("Record with number " + musicAlbum.getNumber() + " was inserted.");
                        } catch (SQLException e) {
                            connection.rollback();
                            throw e;
                        }
                    }
            }
        }

    }

    @Override
    public MusicAlbum read(int number) throws SQLException {
        String sql = "SELECT * FROM music_albums WHERE number = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, number);

            statement.executeUpdate();
            connection.commit();
        }
        return null;
    }
    @Override
    public void update(MusicAlbum musicAlbum) throws SQLException {
        String sql = "UPDATE music_albums SET year = ?, album = ?, artist = ?, genre = ?, subgenre = ? " +
                "WHERE number = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, musicAlbum.getNumber());
            statement.setInt(2, musicAlbum.getYear());
            statement.setString(3, musicAlbum.getAlbum());
            statement.setString(4, musicAlbum.getArtist());
            statement.setString(5, musicAlbum.getGenre());
            statement.setString(6, musicAlbum.getSubgenre());
            statement.setInt(7, musicAlbum.getId());

            statement.executeUpdate();
            connection.commit();
        }
    }

    @Override
    public void delete(MusicAlbum musicAlbum) throws SQLException {
        String sql = "DELETE FROM music_albums WHERE number = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, musicAlbum.getId());

            statement.executeUpdate();
            connection.commit();
        }
    }

    @Override
    public List<MusicAlbum> getAll() throws SQLException {

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT a.number, a.year, a.album, ar.name AS artist_name, " +
                             "       string_agg(g.name, ', ') AS genre_names, a.subgenre " +
                             "FROM music_albums a " +
                             "JOIN artists ar ON a.artist = ar.id " +
                             "JOIN album_genres ag ON a.number = ag.id_album " +
                             "JOIN genres g ON ag.id_genre = g.id " +
                             "GROUP BY a.number, ar.name order by a.number");
             ResultSet rs = statement.executeQuery()) {

            List<MusicAlbum> albums = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("number");
                int releaseYear = rs.getInt("year");
                String title = rs.getString("album");
                String artistName = rs.getString("artist_name");
                String genreNames = rs.getString("genre_names");
                String subgenre = rs.getString("subgenre");
                albums.add(new MusicAlbum(id, releaseYear, title, artistName, genreNames, subgenre));
            }
            return albums;
        }
    }

    public void findById(int id) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT a.number, a.year, a.album, ar.name AS artist_name, " +
                             "       string_agg(g.name, ', ') AS genre_names, a.subgenre " +
                             "FROM music_albums a " +
                             "JOIN artists ar ON a.artist = ar.id " +
                             "JOIN album_genres ag ON a.number = ag.id_album " +
                             "JOIN genres g ON ag.id_genre = g.id " +
                             "WHERE a.number = ? " +
                             "GROUP BY a.number, ar.name " +
                             "ORDER BY a.number");
        ) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                MusicAlbum musicAlbum = null;
                while (rs.next()) {
                    int number = rs.getInt("number");
                    int releaseYear = rs.getInt("year");
                    String title = rs.getString("album");
                    String artistName = rs.getString("artist_name");
                    String genreNames = rs.getString("genre_names");
                    String subgenre = rs.getString("subgenre");
                    musicAlbum = new MusicAlbum(number, releaseYear, title, artistName, genreNames, subgenre);
                }
                System.out.println(musicAlbum);
            }
        }
    }

    public void findByYear(int year) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT a.number, a.year, a.album, ar.name AS artist_name, " +
                             "       string_agg(g.name, ', ') AS genre_names, a.subgenre " +
                             "FROM music_albums a " +
                             "JOIN artists ar ON a.artist = ar.id " +
                             "JOIN album_genres ag ON a.number = ag.id_album " +
                             "JOIN genres g ON ag.id_genre = g.id " +
                             "WHERE a.year = ? " +
                             "GROUP BY a.number, ar.name " +
                             "ORDER BY a.number");
        ) {
            statement.setInt(1, year);
            try (ResultSet rs = statement.executeQuery()) {
                List<MusicAlbum> musicAlbum = new ArrayList<>();
                while (rs.next()) {
                    int number = rs.getInt("number");
                    int releaseYear = rs.getInt("year");
                    String title = rs.getString("album");
                    String artistName = rs.getString("artist_name");
                    String genreNames = rs.getString("genre_names");
                    String subgenre = rs.getString("subgenre");
                    musicAlbum.add(new MusicAlbum(number, releaseYear, title, artistName, genreNames, subgenre));
                }
                System.out.println(musicAlbum);
            }
        }

    }

    public void findByArtist(String artist) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT a.number, a.year, a.album, ar.name AS artist_name, " +
                             "       string_agg(g.name, ', ') AS genre_names, a.subgenre " +
                             "FROM music_albums a " +
                             "JOIN artists ar ON a.artist = ar.id " +
                             "JOIN album_genres ag ON a.number = ag.id_album " +
                             "JOIN genres g ON ag.id_genre = g.id " +
                             "WHERE ar.name = ? " +
                             "GROUP BY a.number, ar.name " +
                             "ORDER BY a.number");
        ) {
            statement.setString(1, artist);
            try (ResultSet rs = statement.executeQuery()) {
                List<MusicAlbum> musicAlbum = new ArrayList<>();
                while (rs.next()) {
                    int number = rs.getInt("number");
                    int releaseYear = rs.getInt("year");
                    String title = rs.getString("album");
                    String artistName = rs.getString("artist_name");
                    String genreNames = rs.getString("genre_names");
                    String subgenre = rs.getString("subgenre");
                    musicAlbum.add(new MusicAlbum(number, releaseYear, title, artistName, genreNames, subgenre));
                }
                System.out.println(musicAlbum);
            }
        }
    }

    public void findByGenre(String genre) throws SQLException
    {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT a.number, a.year, a.album, ar.name AS artist_name, " +
                             "       string_agg(g.name, ', ') AS genre_names, a.subgenre " +
                             "FROM music_albums a " +
                             "JOIN artists ar ON a.artist = ar.id " +
                             "JOIN album_genres ag ON a.number = ag.id_album " +
                             "JOIN genres g ON ag.id_genre = g.id " +
                             "WHERE g.name = ? " +
                             "GROUP BY a.number, ar.name " +
                             "ORDER BY a.number");
        ) {
            statement.setString(1, genre);
            try (ResultSet rs = statement.executeQuery()) {
                List<MusicAlbum> musicAlbum = new ArrayList<>();
                while (rs.next()) {
                    int number = rs.getInt("number");
                    int releaseYear = rs.getInt("year");
                    String title = rs.getString("album");
                    String artistName = rs.getString("artist_name");
                    String genreNames = rs.getString("genre_names");
                    String subgenre = rs.getString("subgenre");
                    musicAlbum.add(new MusicAlbum(number, releaseYear, title, artistName, genreNames, subgenre));
                }
                System.out.println(musicAlbum);
            }
        }
    }

    public int findGenreByName(String name) throws SQLException {
        String sql = "select id from genres where name='" + name + "'";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    public int findArtistByName(String name) throws SQLException {
        String sql = "select id from artists where name='" + name + "'";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

}
