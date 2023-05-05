package org.example.Bonus.DAO;

import org.example.Bonus.Database.Database;
import org.example.Bonus.Entities.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAOImpl implements ArtistDAO {

    private final Database connectionPool;

    public ArtistDAOImpl(Database connectionPool) {
        this.connectionPool = connectionPool;
    }
    @Override
    public void create(Artist artist) throws SQLException {
        String sql = "INSERT INTO artists (name) " +
                "VALUES (?)";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Check if a record with the same "number" value already exists in the table
            String checkSql = "SELECT COUNT(*) FROM artists WHERE name = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
                checkStatement.setString(1, artist.getName());
                ResultSet resultSet = checkStatement.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);
                if (count > 0) {
                    //System.out.println("Record with number " + artist.getName() + " already exists.");
                    return;
                }
            }
            statement.setString(1, artist.getName());
            statement.executeUpdate();
            connection.commit();
        }
    }

    @Override
    public Artist read(int number) throws SQLException {
        String sql = "SELECT * FROM artists WHERE id = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, number);

            statement.executeUpdate();
            connection.commit();
        }
        return null;
    }
    @Override
    public void update(Artist artist) throws SQLException {
        String sql = "UPDATE artists SET id = ?, name = ? " +
                "WHERE id = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, artist.getId());
            statement.setString(2, artist.getName());

            statement.executeUpdate();
            connection.commit();
        }
    }

    @Override
    public void delete(Artist artist) throws SQLException {
        String sql = "DELETE FROM artists WHERE id = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, artist.getId());

            statement.executeUpdate();
            connection.commit();
        }
    }

    @Override
    public List<Artist> getAll() throws SQLException {

        String sql = "SELECT * FROM artists";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            try (var resultSet = statement.executeQuery()) {
                List<Artist> artists = new ArrayList<>();

                while (resultSet.next()) {
                    artists.add(new Artist(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }

                return artists;
            }
        }
    }

    public void findByName(String name) throws SQLException {
        String sql = "SELECT * FROM artists WHERE name = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);

            try (var resultSet = statement.executeQuery()) {
                List<Artist> artists = new ArrayList<>();

                while (resultSet.next()) {
                    artists.add(new Artist(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }

                System.out.println(artists);
            }
        }
    }

    public void findByID(int id) throws SQLException {
        String sql = "SELECT * FROM artists WHERE id = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (var resultSet = statement.executeQuery()) {
                List<Artist> artists = new ArrayList<>();

                while (resultSet.next()) {
                    artists.add(new Artist(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }

                System.out.println(artists);
            }
        }
    }


}
