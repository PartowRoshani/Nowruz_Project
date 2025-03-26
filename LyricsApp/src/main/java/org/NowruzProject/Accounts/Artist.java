package org.NowruzProject.Accounts;

import org.NowruzProject.AccountType;
import org.NowruzProject.Music.Song;
import org.NowruzProject.Music.Album;
import java.util.ArrayList;
import java.util.List;

public class Artist extends Account {
    private List<String> songs;  // the list of artist's music
    private List<String> albums; // the lists of artist's albums
    private boolean approved;
    private boolean active;
    public Artist(String username, String password, String email, String fullName, int age) {
        super(username, password, email, fullName, age, AccountType.ARTIST);
        this.songs = new ArrayList<>();
        this.albums = new ArrayList<>();
        this.approved = false;
        this.active = true;

    }

    // Method for add a song
    public void addSong(String songName) {
        songs.add(songName);
        System.out.println(getUsername() + " added a new song: " + songName);
    }

    // Method for add an album
    public void addAlbum(String albumName) {
        albums.add(albumName);
        System.out.println(getUsername() + " released a new album: " + albumName);
    }

    // show the list of musics or albums
    public void showSongs() {
        System.out.println(getUsername() + "'s Songs: " + songs);
    }

    public void showAlbums() {
        System.out.println(getUsername() + "'s Albums: " + albums);
    }

    public void editSong(Song song, String newLyrics) {
        if (songs.contains(song)) {
            song.setLyrics(newLyrics); // change the lyric
            System.out.println("Song lyrics updated: " + song.getTitle());
        } else {
            System.out.println("You do not have permission to edit this song.");
        }
    }

    //method for approved or reject the artist
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    //getter
    public boolean isApproved() {
        return approved;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
