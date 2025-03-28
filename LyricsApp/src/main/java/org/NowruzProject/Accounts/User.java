package org.NowruzProject.Accounts;

import org.NowruzProject.Accounts.AccountType;
import org.NowruzProject.Music.Song;
import java.util.ArrayList;
import java.util.List;

public class User extends Account {
    private final List<Artist> followingArtists;  // Artist list

    public User(String username, String password, String email, String fullName, int age) {
        super(username, password, email, fullName, age, AccountType.USER);
        this.followingArtists = new ArrayList<>();
    }

    // Method for follow artists
    public void followArtist(Artist artist) {
        if (!followingArtists.contains(artist)) {
            followingArtists.add(artist);
            System.out.println(getUsername() + " is now following " + artist.getUsername());
        } else {
            System.out.println(getUsername() + " is already following " + artist.getUsername());
        }
    }

    // Method for show the list of following artist
    public void showFollowingArtists() {
        if (followingArtists.isEmpty()) {
            System.out.println(getUsername() + " is not following any artist.");
        } else {
            System.out.println(getUsername() + " is following:");
            for (Artist artist : followingArtists) {
                System.out.println("- " + artist.getUsername());
            }
        }
    }

    //View the lyrics of  music
    public void viewLyrics(Song song) {
        System.out.println("Lyrics for " + song.getTitle() + ":");
        System.out.println(song.getLyrics());
    }

    //Request for edit the lyrics of music
    public void requestLyricsEdit(Song song, String newLyrics) {
        song.addLyricsEditRequest(this, newLyrics);
        System.out.println("Lyrics edit request sent for " + song.getTitle());
    }

    //Add comment
    public void commentOnSong(Song song, String comment) {
        song.addComment(this, comment);
        System.out.println("Comment added to " + song.getTitle());
    }

    //Like songs
    public void likeSong(Song song) {
        song.like(this);
    }

    //dislike songs
    public void dislikeSong(Song song) {
        song.dislike(this);
    }

    // Suggest songs based on followed artists
    public void showSuggestions() {
        //suggestion based on followed artists
        System.out.println(getUsername() + "'s Suggested Songs:");
        List<Song> suggestedSongs = new ArrayList<>();

        // add songs to the list
        for (Artist artist : followingArtists) {
            for (Song song : artist.getSongs()) {
                if (!suggestedSongs.contains(song)) {
                    suggestedSongs.add(song);
                    System.out.println(song.getTitle() + " by " + artist.getUsername());
                }
            }
        }

        // no suggest is available
        if (suggestedSongs.isEmpty()) {
            System.out.println("No suggestions available.");
        }
    }
}
