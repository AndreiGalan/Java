package org.example.Bonus.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private LocalDateTime creationTimestamp;
    private List<MusicAlbum> albums;

    public Playlist(String name, LocalDateTime creationTimestamp) {
        this.name = name;
        this.creationTimestamp = creationTimestamp;
        this.albums = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public List<MusicAlbum> getAlbums() {
        return albums;
    }

    public void addAlbum(MusicAlbum album) {
        albums.add(album);
    }

    public void removeAlbum(MusicAlbum album) {
        albums.remove(album);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist: ").append(name).append("\n");
        sb.append("Created: ").append(creationTimestamp.toString()).append("\n");
        sb.append("Albums:\n");
        for (MusicAlbum album : albums) {
            sb.append(album.toString()).append("\n");
        }
        return sb.toString();
    }
}
