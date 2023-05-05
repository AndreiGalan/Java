package org.example.Bonus.Entities;

public class MusicAlbum {
    private int number;
    private int year;
    private String album;
    private String artist;
    private String genre;
    private String subgenre;

    public MusicAlbum() {
    }

    public MusicAlbum(int number, int year, String album, String artist, String genre, String subgenre) {
        this.number = number;
        this.year = year;
        this.album = album;
        this.artist = artist;
        this.genre = genre;
        this.subgenre = subgenre;
    }

    public int getNumber() {
        return number;
    }

    public int getYear() {
        return year;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getSubgenre() {
        return subgenre;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }


    @Override
    public String toString() {
        return "Album{" +
                "number=" + number +
                ", year=" + year +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", subgenre='" + subgenre + '\'' +
                '}' + '\n';
    }

    public int getId() {
        return number;
    }
}
