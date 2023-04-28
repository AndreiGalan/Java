package org.example.Compulsory;

import org.example.Compulsory.Album;
import org.example.Compulsory.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumDAO {

    public void create(int releaseYear, String title, String artistName, String genreNames) throws SQLException {

        try (Connection con = Database.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement pstmt = con.prepareStatement(
                    "insert into albums (release_year, title, artist_id) values (?, ?, ?)")) {
                pstmt.setInt(1, releaseYear);
                pstmt.setString(2, title);
                pstmt.setInt(3, findArtistByName(artistName));
                pstmt.executeUpdate();
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }

        int albumId = findAlbumByTitle(title);
        String[] genres = genreNames.split(",");
        for (String genre : genres) {
            int genreId = findGenreByName(genre.trim());
            if (genreId != 0) {
                try (Connection con = Database.getConnection()) {
                    con.setAutoCommit(false);
                    try (PreparedStatement pstmt = con.prepareStatement(
                            "insert into album_genre (album_id, genre_id) values (?, ?)")) {
                        pstmt.setInt(1, albumId);
                        pstmt.setInt(2, genreId);
                        pstmt.executeUpdate();
                        con.commit();
                    } catch (SQLException e) {
                        con.rollback();
                        throw e;
                    }
                }
            }
        }
    }

    public List<Album> findAll() throws SQLException {
        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                     "SELECT a.id, a.release_year, a.title, ar.name AS artist_name, " +
                             "       string_agg(g.name, ', ') AS genre_names " +
                             "FROM albums a " +
                             "JOIN artists ar ON a.artist_id = ar.id " +
                             "JOIN album_genre ag ON a.id = ag.album_id " +
                             "JOIN genres g ON ag.genre_id = g.id " +
                             "GROUP BY a.id, ar.name");
             ResultSet rs = pstmt.executeQuery()) {

            List<Album> albums = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int releaseYear = rs.getInt("release_year");
                String title = rs.getString("title");
                String artistName = rs.getString("artist_name");
                String genreNames = rs.getString("genre_names");
                albums.add(new Album(id, releaseYear, title, artistName, genreNames));
            }
            return albums;
        }
    }


    private List<Integer> getGenreIdsForAlbum(int albumId) throws SQLException {
        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                     "SELECT genre_id FROM album_genre WHERE album_id = ?")) {
            pstmt.setInt(1, albumId);
            try (ResultSet rs = pstmt.executeQuery()) {
                List<Integer> genreIds = new ArrayList<>();
                while (rs.next()) {
                    genreIds.add(rs.getInt("genre_id"));
                }
                return genreIds;
            }
        }
    }

    private int findAlbumByTitle(String title) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from albums where title='" + title + "'")) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    private int findArtistByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from artists where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    private int findGenreByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from genres where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

}
