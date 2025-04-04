package org.NowruzProject.Accounts;

import org.NowruzProject.Music.Song;

import java.util.*;

public class User extends Account {
    private final List<Artist> followingArtists;// Artist list
    private final int MAX_HISTORY = 5;
    private LinkedList<Song> viewHistory = new LinkedList<>();

    public User(String username, String password, String email, String fullName, int age) {
        super(username, password, email, fullName, age, AccountType.USER);
        this.followingArtists = new ArrayList<>();
    }






    // Method for follow artists
    public void followArtist(Artist artist) {
        if (artist == null) {
            System.out.println("Artist not found.");
            return;
        }

        // Show artist info
        artist.showArtistInfo();

        // get accept
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to follow this artist? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            if (!followingArtists.contains(artist)) {
                followingArtists.add(artist);
                System.out.println("You are now following " + artist.getUsername() + "!");
            } else {
                System.out.println("You are already following " + artist.getUsername() + ".");
            }
        } else {
            System.out.println("Follow request cancelled.");
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

    public void showNewSongsFromFollowedArtists() {
        System.out.println("\n=== New Songs from Followed Artists ===");
        boolean foundNewSong = false;

        for (Artist artist : followingArtists) {
            List<Song> songs = artist.getSongs();
            int totalSongs = songs.size();

            // if artist has more than 1 music
            if (totalSongs > 1) {
                Song latestSong = songs.get(totalSongs - 1);
                Song secondLatestSong = songs.get(totalSongs - 2);

                System.out.println("- " + latestSong.getTitle() + " by " + artist.getUsername() + " (Released: " + latestSong.getReleaseDate() + ")");
                System.out.println("- " + secondLatestSong.getTitle() + " by " + artist.getUsername() + " (Released: " + secondLatestSong.getReleaseDate() + ")");
                foundNewSong = true;
            }
        }

        if (!foundNewSong) {
            System.out.println("No new songs from followed artists.");
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



    //add to history
    public void addToHistory(Song song) {
        viewHistory.remove(song); // if visited before , remove it and add it at first
        viewHistory.addFirst(song); //

        if (viewHistory.size() > MAX_HISTORY) {
            viewHistory.removeLast(); // if it's more than 5 remove the last one
        }
    }

    // history getter
    public List<Song> getViewHistory() {
        return Collections.unmodifiableList(viewHistory); // users cant change this list by themselves
    }

}
