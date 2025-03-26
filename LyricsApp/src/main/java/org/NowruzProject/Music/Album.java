package org.NowruzProject.Music;

import org.NowruzProject.Accounts.Artist;
import java.util.ArrayList;
import java.util.List;

public class Album {
    private final String title;
    private final String releaseDate;
    private final Artist artist;
    private final List<Song> songs;

    // constructor
    public Album(String title, String releaseDate, Artist artist) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.songs = new ArrayList<>();  // Songs list
    }

    // add song to album
    public void addSong(Song song) {
        songs.add(song);
        System.out.println("Song " + song.getTitle() + " added to album: " + title);
    }

    // show list of songs
    public void showSongs() {
        System.out.println("Songs in album " + title + ":");
        for (Song song : songs) {
            System.out.println("- " + song.getTitle());
        }
    }

    // getters and setters
    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Artist getArtist() {
        return artist;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void showAlbumInfo() {
        System.out.println("Album Title: " + title);
        System.out.println("Release Date: " + releaseDate);
        System.out.println("Artist: " + artist.getUsername());
    }

}
