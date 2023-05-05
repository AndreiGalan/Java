package org.example.Bonus.DAO;

import org.example.Bonus.Entities.Artist;

import java.sql.SQLException;
import java.util.List;

public interface ArtistDAO {
    void create(Artist artist) throws SQLException;
    Artist read(int number) throws SQLException;
    void update(Artist artist) throws SQLException;
    void delete(Artist artist) throws SQLException;
    List<Artist> getAll() throws SQLException;
}
