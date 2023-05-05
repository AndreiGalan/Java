package org.example.Compulsory.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artists")
@NamedQueries({
        @NamedQuery(name = "ArtistJPA.findAll", query = "select e from ArtistJPA e order by e.name"),
        @NamedQuery(name = "ArtistJPA.findByName", query = "SELECT a FROM ArtistJPA a WHERE a.name LIKE :name")
})


public class ArtistJPA{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<AlbumJPA> albums;

    public ArtistJPA() {
    }
    public ArtistJPA(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ArtistJPA{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
