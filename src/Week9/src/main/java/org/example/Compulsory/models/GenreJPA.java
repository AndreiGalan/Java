package org.example.Compulsory.models;

import javax.persistence.*;
@Entity
@Table(name = "genres")
@NamedQueries({
        @NamedQuery(name = "GenreJPA.findAll", query = "select e from GenreJPA e order by e.name"),
        @NamedQuery(name = "GenreJPA.findByName", query = "SELECT a FROM GenreJPA a WHERE a.name LIKE :name")
})

public class GenreJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    public GenreJPA() {
    }
    public GenreJPA(String name) {
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
        return "GenreJPA{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
