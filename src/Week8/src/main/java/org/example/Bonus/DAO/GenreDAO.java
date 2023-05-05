package org.example.Bonus.DAO;

import org.example.Bonus.Entities.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenreDAO {
    void create(Genre genre) throws SQLException;
    Genre read(int number) throws SQLException;
    void update(Genre genre) throws SQLException;
    void delete(Genre genre) throws SQLException;
    List<Genre> getAll() throws SQLException;
    int getGenreId(String genreName) throws SQLException;
}
