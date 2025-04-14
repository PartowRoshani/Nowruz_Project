package org.NowruzProject.Music;

import org.NowruzProject.Accounts.Artist;
import org.NowruzProject.Accounts.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MusicManager {
    // All songs list
    private static List<Song> allSongs = new ArrayList<>();

    // add song to general list
    public static void addSong(Song song) {
        allSongs.add(song);
    }

    // find song by title
    public static Song findSongByTitle(String title) {
        for (Song song : allSongs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null; // if the music not available
    }

    // Search music by artist
    public static List<Song> findSongsByArtist(String artistName) {
        List<Song> result = new ArrayList<>();
        for (Song song : allSongs) {
            if (song.getArtist().getUsername().equalsIgnoreCase(artistName)) {
                result.add(song);
            }
        }
        return result;
    }

    //show all songs
    public static void showAllSongs() {
        if (allSongs.isEmpty()) {
            System.out.println("No songs available.");
        } else {
            System.out.println("\n=== All Songs ===");
            for (Song song : allSongs) {
                System.out.println(song.getTitle() + " by " + song.getArtist().getUsername());
            }
        }
    }

    // Show special artist's songs
    public static void showSongsByArtist(Artist artist) {
        List<Song> songs = findSongsByArtist(artist.getUsername());
        if (songs.isEmpty()) {
            System.out.println(artist.getUsername() + " has no songs.");
        } else {
            System.out.println("\n=== Songs by " + artist.getUsername() + " ===");
            for (Song song : songs) {
                System.out.println(song.getTitle());
            }
        }
    }

    // devitalises of a special song
    public static void showSongDetails(Song song) {
        if (song == null) {
            System.out.println("Song not found.");
        } else {
            System.out.println("\n=== Song Details ===");
            System.out.println("Title: " + song.getTitle());
            System.out.println("Artist: " + song.getArtist().getUsername());
            System.out.println("Release Date: " + song.getReleaseDate());
            System.out.println("Genre: " + song.getGenre());
            System.out.println("Views: " + song.getViewsCount());
            System.out.println("Lyrics:\n" + song.getLyrics());
        }
    }

    // Method for show all songs in the App
    public static List<Song> getAllSongs() {
        return allSongs;
    }

    // remove a song form list
    public static void removeSong(Song song) {
        allSongs.remove(song);
    }


    public static Artist getArtistByUsername(String username) {
        for (Artist artist : Artist.getAllArtists()) { // artists list
            if (artist.getUsername().equals(username)) {
                return artist;
            }
        }
        return null;
    }

    private void searchAlbumByName(List<Artist> artists, Scanner scanner) {
        System.out.print("Enter album title: ");
        String searchTitle = scanner.nextLine();

        for (Artist artist : artists) {
            for (Album album : artist.getAlbums()) {
                if (album.getTitle().equalsIgnoreCase(searchTitle)) {
                    System.out.println("Found album: " + album.getTitle() + " by " + artist.getFullName());
                    System.out.println(album.showAlbumInfo());

                    List<Song> songs = album.getSongs();
                    for (int i = 0; i < songs.size(); i++) {
                        System.out.println((i + 1) + ". " + songs.get(i).getTitle());
                    }

                    System.out.print("Choose a song to view details or 0 to cancel: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice > 0 && choice <= songs.size()) {
                        Song Choice = songs.get(choice - 1);
                        System.out.println(songs.get(choice - 1).getDetails(Choice));
                    }
                    return;
                }
            }
        }

        System.out.println("Album not found.");
    }


}