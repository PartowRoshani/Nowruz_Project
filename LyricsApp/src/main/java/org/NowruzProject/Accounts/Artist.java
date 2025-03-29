package org.NowruzProject.Accounts;

import org.NowruzProject.Accounts.AccountType;
import org.NowruzProject.Music.Song;
import org.NowruzProject.Music.Album;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Artist extends Account {
    private List<Song> songs;  // the list of artist's music
    private List<Album> albums; // the lists of artist's albums
    private static Set<Artist> allArtists = new HashSet<>();
    private boolean approved;
    private boolean active;
    public Artist(String username, String password, String email, String fullName, int age) {
        super(username, password, email, fullName, age, AccountType.ARTIST);
        this.songs = new ArrayList<>();
        this.albums = new ArrayList<>();
        this.approved = false;
        this.active = true;
        allArtists.add(this);

    }

    // Method for add a song
    public void addSong(Song song) {
        songs.add(song);
        System.out.println("Song " + song.getTitle() + " added by " + getUsername());
    }


    // Method to get the list of songs by the artist
    public List<Song> getSongs() {
        return songs;
    }

    // Method for add an album
    public void addAlbum(Album albumName) {
        albums.add(albumName);
        System.out.println(getUsername() + " released a new album: " + albumName);
    }

    //Method for remove songs or album
    public void removeSong(Song song) {
        songs.remove(song);
        System.out.println("Song " + song.getTitle() + " removed by " + getUsername());
    }

    public void removeAlbum(String albumName) {
        albums.remove(albumName);
        System.out.println(getUsername() + " removed album: " + albumName);
    }


    // show the list of musics or albums
    public void showSongs() {
        System.out.println(getUsername() + "'s Songs:");
        if (songs.isEmpty()) {
            System.out.println("No songs available.");
        } else {
            for (Song song : songs) {
                System.out.println("- " + song.getTitle() + " (Released: " + song.getReleaseDate() + ", Genre: " + song.getGenre() + ")");
            }
        }
    }


    public void showAlbums() {
        System.out.println(getUsername() + "'s Albums:");
        if (albums.isEmpty()) {
            System.out.println("No albums available.");
        } else {
            for (Album album : albums) {
                System.out.println("- " + album.getTitle() + " (Released: " + album.getReleaseDate() + ")");
            }
        }
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

    public static Set<Artist> getAllArtists() {
        return allArtists;
    }





    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Album> getAlbums() {
        return albums;
    }




    //  viewAlbums
    public void viewAlbums() {
        showAlbums();
    }

    public void showArtistInfo() {
        System.out.println("\n=== Artist Information ===");
        System.out.println("Name: " + getFullName());
        System.out.println("Username: " + getUsername());
        System.out.println("Approved: " + (isApproved() ? "Yes" : "No"));
        System.out.println("Active: " + (isActive() ? "Yes" : "No"));
        System.out.println("Number of Songs: " + songs.size());
        System.out.println("Number of Albums: " + albums.size());
    }


}
