package org.NowruzProject.Music;

import org.NowruzProject.Accounts.Artist;
import org.NowruzProject.Accounts.User;
import org.NowruzProject.Comment;

import java.util.ArrayList;
import java.util.List;

public class Song {

    private final String title;
    private String lyrics;
    private final Artist artist;
    private final String releaseDate;
    private final List<Comment> comments; // comments list
    private final List<User> likedBy;     // like by list
    private final List<User> dislikedBy;  // dislike by list
    private final List<String> editRequests; // edit requests list
    private final Album album;
    private final Genre genre;
    private int viewsCount;
    private final List<String> tags;

    // constructor
    public Song(String title, Artist artist, String releaseDate, Album album, Genre genre, int viewsCount, List<String> tags) {
        this.title = title;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.album = album;
        this.genre = genre;
        this.viewsCount = viewsCount;
        this.tags = new ArrayList<>(tags);
        this.lyrics = "";
        this.comments = new ArrayList<>();
        this.likedBy = new ArrayList<>();
        this.dislikedBy = new ArrayList<>();
        this.editRequests = new ArrayList<>();
    }

    // Method for set the lyrics
    public void setLyrics(String newLyrics) {
        this.lyrics = newLyrics;
    }

    // Method for get the artist's name
    public Artist getArtist() {
        return artist;
    }

    // Method for get the title
    public String getTitle() {
        return title;
    }

    // lyric getter
    public String getLyrics() {
        return lyrics;
    }

    //tags getter
    public List<String> getTags() {
        return List.copyOf(tags);
    }


    // Method for send the edit requests
    public void addLyricsEditRequest(User user, String newLyrics) {
        editRequests.add("User " + user.getUsername() + " requests to change lyrics to: " + newLyrics);
        System.out.println("Lyrics edit request sent for " + title + " by " + user.getUsername());
    }

    // Method for add comments
    public void addComment(User user, String text) {
        comments.add(new Comment(text, user));
        System.out.println("Comment added by " + user.getUsername() + " to " + title);
    }

    // Method for like the music
    public void like(User user) {
        if (dislikedBy.contains(user)) {
            dislikedBy.remove(user);
        }
        if (!likedBy.contains(user)) {
            likedBy.add(user);
            System.out.println(user.getUsername() + " liked the song: " + title);
        } else {
            System.out.println(user.getUsername() + " has already liked the song: " + title);
        }
    }

    // Method for dislike the music
    public void dislike(User user) {
        if (likedBy.contains(user)) {
            likedBy.remove(user);
        }
        if (!dislikedBy.contains(user)) {
            dislikedBy.add(user);
            System.out.println(user.getUsername() + " disliked the song: " + title);
        } else {
            System.out.println(user.getUsername() + " has already disliked the song: " + title);
        }
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments); // Send a copy for save original data
    }

    public List<String> getEditRequests() {
        return new ArrayList<>(editRequests);  // Send a copy
    }

    //count likes and dislikes
    public int getLikesCount() {
        return likedBy.size();
    }

    public int getDislikesCount() {
        return dislikedBy.size();
    }

    //count viewers
    public void increaseViewCount() {
        viewsCount++;
    }

    //getters
    public int getViewsCount() {
        return viewsCount;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Album getAlbum() {
        return album;
    }

    public Genre getGenre() {
        return genre;
    }


}
