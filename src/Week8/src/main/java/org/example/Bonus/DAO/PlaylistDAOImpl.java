package org.example.Bonus.DAO;

import org.example.Bonus.Entities.MusicAlbum;
import org.example.Bonus.Entities.Playlist;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class PlaylistDAOImpl implements PlaylistDAO {
    private MusicAlbumDAOImpl musicAlbumDAOImpl;

    public PlaylistDAOImpl(MusicAlbumDAOImpl musicAlbumDAOImpl) {
        this.musicAlbumDAOImpl = musicAlbumDAOImpl;
    }
    @Override
    public List<Playlist> generateMaximalPlaylists(int maximumPlaylists) throws SQLException {
        List<MusicAlbum> allAlbums = musicAlbumDAOImpl.getAll();
        Map<MusicAlbum, Set<MusicAlbum>> relatedAlbumsMap = new HashMap<>();
        for(MusicAlbum album : allAlbums) {
            Set<MusicAlbum> relatedAlbums = new HashSet<>();
            for(MusicAlbum otherAlbum : allAlbums) {
                String[] albumGenres = album.getGenre().split(",");
                String[] otherAlbumGenres = otherAlbum.getGenre().split(",");
                boolean genresOverlap = Arrays.stream(albumGenres)
                        .anyMatch(genre -> Arrays.asList(otherAlbumGenres).contains(genre));
                if(album != otherAlbum && (album.getArtist().equals(otherAlbum.getArtist()) || album.getYear() == otherAlbum.getYear() || genresOverlap)) {
                    relatedAlbums.add(otherAlbum);
                }
            }
            relatedAlbumsMap.put(album, relatedAlbums);
        }
        List<Playlist> maximalPlaylists = new ArrayList<>();
        int contor = 1;
        int maximumSize = 0;
        for (MusicAlbum album : allAlbums) {
            if (!isPlaylistValid(album, maximalPlaylists)) {
                Playlist newPlaylist = new Playlist("Playlist " + contor, LocalDateTime.now());
                newPlaylist.addAlbum(album);
                addUnrelatedAlbumsToPlaylist(album, relatedAlbumsMap, newPlaylist, allAlbums);
                maximalPlaylists.add(newPlaylist);
                if(newPlaylist.getAlbums().size() > maximumSize) {
                    maximumSize = newPlaylist.getAlbums().size();
                }
                contor++;
            }
        }

        //remove playlists that are not maximal
        int finalMaximumSize = maximumSize;
        maximalPlaylists.removeIf(playlist -> playlist.getAlbums().size() < finalMaximumSize);

        return maximalPlaylists.subList(0, Math.min(maximumPlaylists, maximalPlaylists.size()));
    }

    @Override
    public boolean isPlaylistValid(MusicAlbum album, List<Playlist> playlists) {
        for (Playlist playlist : playlists) {
            if (playlist.getAlbums().contains(album)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addUnrelatedAlbumsToPlaylist(MusicAlbum album, Map<MusicAlbum, Set<MusicAlbum>> relatedAlbumsMap,
                                             Playlist playlist, List<MusicAlbum> allAlbums) {
        boolean addedUnrelatedAlbum = false;
        for (MusicAlbum otherAlbum : allAlbums) {
            boolean isUnrelated = true;
            for(MusicAlbum relatedAlbum : playlist.getAlbums()) {
                if (relatedAlbumsMap.get(relatedAlbum).contains(otherAlbum) ||
                        playlist.getAlbums().contains(otherAlbum)) {
                    isUnrelated = false;
                    break;
                }
            }
            if (isUnrelated) {
                addedUnrelatedAlbum = true;
                playlist.addAlbum(otherAlbum);
                addUnrelatedAlbumsToPlaylist(otherAlbum, relatedAlbumsMap, playlist, allAlbums);
            }
        }
    }

}
