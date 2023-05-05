package org.example.Bonus.DAO;

import org.example.Bonus.Entities.MusicAlbum;
import org.example.Bonus.Entities.Playlist;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PlaylistDAO {
    List<Playlist> generateMaximalPlaylists(int maximumPlaylists) throws SQLException;
    boolean isPlaylistValid(MusicAlbum musicAlbum, List<Playlist> playlist);
    void addUnrelatedAlbumsToPlaylist(MusicAlbum album, Map<MusicAlbum, Set<MusicAlbum>> relatedAlbumsMap, Playlist playlist, List<MusicAlbum> allAlbums);
}
