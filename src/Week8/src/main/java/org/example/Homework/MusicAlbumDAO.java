package org.example.Homework;

import java.sql.SQLException;
import java.util.List;

public interface MusicAlbumDAO {
    void create(MusicAlbum musicAlbum) throws SQLException;
    MusicAlbum read(int number) throws SQLException;
    void update(MusicAlbum musicAlbum) throws SQLException;
    void delete(MusicAlbum musicAlbum) throws SQLException;
    List<MusicAlbum> getAll() throws SQLException;
}
