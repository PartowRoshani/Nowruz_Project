package org.NowruzProject.Accounts;

import org.NowruzProject.Accounts.AccountType;
import org.NowruzProject.Music.Song;
import org.NowruzProject.Music.Album;

import java.util.*;

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

    //Method for popular songs
    public List<Song> getPopularSongs(int count) {
        if (songs.isEmpty()) {
            System.out.println("No songs available for this artist.");
            return Collections.emptyList();
        }

        // sort music based on likes
        songs.sort(Comparator.comparingInt(Song::getLikesCount).reversed());

        // show some of the songs
        return songs.subList(0, Math.min(count, songs.size()));
    }


    // Method for add a song
    public void addSong(Song song) {
        if (!approved) {
            System.out.println("You must be approved by an admin to add songs.");
            return;
        }
        else {
            songs.add(song);
            System.out.println("Song " + song.getTitle() + " added by " + getUsername());
        }
    }


    // Method to get the list of songs by the artist
    public List<Song> getSongs() {
        return songs;
    }

    // Method for add an album
    public void addAlbum(Album albumName) {
        if (!approved) {
            System.out.println("You must be approved by an admin to release albums.");
            return;
        }
        else {
            albums.add(albumName);
            System.out.println(getUsername() + " released a new album: " + albumName.getTitle());
        }

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
                System.out.println("- " + song.getTitle() + " (Released: " + song.getReleaseDate() + ", Genre: " + song.getGenre() + ", Album: " + (song.getAlbum() != null ? song.getAlbum().getTitle() : "No album")
                        +")" );
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
        System.out.println("\n🔥 Popular Songs:");
        List<Song> popularSongs = getPopularSongs(3);  // 3 top music

        if (popularSongs.isEmpty()) {
            System.out.println("No popular songs available.");
        } else {
            for (int i = 0; i < popularSongs.size(); i++) {
                Song song = popularSongs.get(i);
                System.out.println((i + 1) + ". " + song.getTitle() + " (Likes: " + song.getLikesCount() + ")");
            }
        }
    }

    // Method for approving or rejecting an edit request by the artist
    public void handleEditRequest(Song song, String requestedChange, boolean approve) {
        if (approve) {
            song.setLyrics(requestedChange);
            System.out.println("Change approved. Lyrics updated.");
        } else {
            System.out.println("Change rejected.");
        }
        song.getEditRequests().remove(requestedChange); // Remove request
    }



    public Song findSong(String title) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }
}
