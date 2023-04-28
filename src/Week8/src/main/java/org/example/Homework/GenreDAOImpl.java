package org.example.Homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAOImpl implements GenreDAO{
    private final Database connectionPool;

    public GenreDAOImpl(Database connectionPool) {
        this.connectionPool = connectionPool;
    }
    @Override
    public void create(Genre genre) throws SQLException {
        String sql = "INSERT INTO genres (name) " +
                "VALUES (?)";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Check if a record with the same "number" value already exists in the table
            String checkSql = "SELECT COUNT(*) FROM genres WHERE name = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
                checkStatement.setString(1, genre.getName());
                ResultSet resultSet = checkStatement.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);
                if (count > 0) {
                    //System.out.println("Record with number " + genre.getName() + " already exists.");
                    return;
                }
            }

            statement.setString(1, genre.getName());
            statement.executeUpdate();
            connection.commit();
        }
    }

    @Override
    public Genre read(int number) throws SQLException {
        String sql = "SELECT * FROM genres WHERE id = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, number);

            statement.executeUpdate();
            connection.commit();
        }
        return null;
    }
    @Override
    public void update(Genre genre) throws SQLException {
        String sql = "UPDATE genres SET id = ?, name = ? " +
                "WHERE id = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, genre.getId());
            statement.setString(2, genre.getName());

            statement.executeUpdate();
            connection.commit();
        }
    }

    @Override
    public void delete(Genre genre) throws SQLException {
        String sql = "DELETE FROM genres WHERE id = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, genre.getId());

            statement.executeUpdate();
            connection.commit();
        }
    }

    @Override
    public List<Genre> getAll() throws SQLException {

        String sql = "SELECT * FROM genres";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            try (var resultSet = statement.executeQuery()) {
                List<Genre> genres = new ArrayList<>();

                while (resultSet.next()) {
                    genres.add(new Genre(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }

                return genres;
            }
        }
    }

    public int getGenreId(String genreName) throws SQLException {
        String sql = "SELECT id FROM genres WHERE name = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, genreName);

            try (var resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt("id");
            }
        }
    }

    public void findByName(String name) throws SQLException {
        String sql = "SELECT * FROM genres WHERE name = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);

            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name"));
                }
            }
        }
    }

    public void findByID(int id) throws SQLException {
        String sql = "SELECT * FROM genres WHERE id = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name"));
                }
            }
        }
    }

}
