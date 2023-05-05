package org.example.Compulsory.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "albums")
@NamedQueries(
        {
                @NamedQuery(name = "AlbumJPA.findAll", query = "select e from AlbumJPA e order by e.title"),
                @NamedQuery(name = "AlbumJPA.findByName", query = "SELECT a FROM AlbumJPA a WHERE a.title LIKE :title"),
                @NamedQuery(name = "AlbumJPA.findByArtist", query = "SELECT a FROM AlbumJPA a WHERE a.artist.name LIKE :artist"),
                @NamedQuery(name = "AlbumJPA.findByGenre", query = "SELECT a FROM AlbumJPA a JOIN a.genres g WHERE g.name LIKE :genre")
        }
)
public class AlbumJPA implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "release_year")
    private int releaseYear;
    @Column(name = "title")
    private String title;
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    @ManyToOne
    private ArtistJPA artist;

    @ManyToMany
    @JoinTable(
            name = "album_genre",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<GenreJPA> genres;

    public AlbumJPA() {
    }

    public AlbumJPA(int releaseYear, String title, ArtistJPA artist , List<GenreJPA> genres) {
        this.releaseYear = releaseYear;
        this.title = title;
        this.artist = artist;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArtistJPA getArtist() {
        return artist;
    }

    public void setArtist(ArtistJPA artist) {
        this.artist = artist;
    }

    public List<GenreJPA> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreJPA> genres) {
        this.genres = genres;
    }


    @Override
    public String toString() {
        return "AlbumJPA{" +
                "id=" + id +
                ", releaseYear=" + releaseYear +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                '}';
    }
}
