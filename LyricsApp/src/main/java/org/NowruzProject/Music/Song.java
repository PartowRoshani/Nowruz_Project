package org.NowruzProject.Music;

import org.NowruzProject.Accounts.Artist;
import org.NowruzProject.Accounts.User;
import org.NowruzProject.AnswerAndQuestion.Question;
import org.NowruzProject.Comments.Comment;

import java.util.ArrayList;
import java.util.List;

import static org.NowruzProject.ColoredOutput.CYAN;

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
    private final List<Artist> collaborators;
    private static List<Question> questions;
    // constructor
    public Song(String title, Artist artist, String releaseDate, Album album, Genre genre, int viewsCount) {
        this.title = title;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.album = album;
        this.genre = genre;
        this.viewsCount = viewsCount;
        this.lyrics = "";
        this.comments = new ArrayList<>();
        this.likedBy = new ArrayList<>();
        this.dislikedBy = new ArrayList<>();
        this.editRequests = new ArrayList<>();
        this.collaborators = new ArrayList<>();
        this.questions = new ArrayList<>();
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


    //collaborators getter
    public List<Artist> getCollaborators() {
        return collaborators;
    }

    public void addCollaborator(Artist collaborator) {
        if (!collaborators.contains(collaborator)) {
            collaborators.add(collaborator);
            collaborator.addSong(this);  // Add music to collaborator's song list
            System.out.println(collaborator.getUsername() + " added as a collaborator on song: " + this.title);
        } else {
            System.out.println(collaborator.getUsername() + " is already a collaborator on this song.");
        }
    }



    // Method for send the edit requests
    public void addLyricsEditRequest(User user, String newLyrics) {
        editRequests.add("User " + user.getUsername() + " requests to change lyrics to: " + newLyrics);
        System.out.println("Lyrics edit request sent for " + title + " by " + user.getUsername());
    }

    public void approveEditRequest(int requestIndex, boolean approve) {
        if (requestIndex >= 0 && requestIndex < editRequests.size()) {
            String requestedLyrics = editRequests.get(requestIndex).split(": ")[1];
            if (approve) {
                setLyrics(requestedLyrics); // Apply the new lyrics if approved
                System.out.println("Lyrics for " + title + " updated to: " + requestedLyrics);
            } else {
                System.out.println("Lyrics edit request for " + title + " rejected.");
            }
            editRequests.remove(requestIndex); // Remove the request after handling
        } else {
            System.out.println("Invalid request index.");
        }
    }


    // Method for add comments
    public void addComment(User user, String text) {
        comments.add(new Comment(text, user ));
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

    public void displayComments() {
        System.out.println("Comments for song: " + this.title);
        if (comments.isEmpty()) {
            System.out.println("No comments yet.");
        } else {
            int i = 1;
            for (Comment comment : comments) {

                System.out.println(i+" - " + comment.getUser().getUsername() + ": " + comment.getText());
                System.out.println("  👍 " + comment.getLikeCount() + " | 👎 " + comment.getDislikeCount());
                i++;
            }

        }
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
        this.viewsCount++;
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


    public static void addQuestion(Question question) {
        questions.add(question);
    }

    public static void displayQuestionsForSong(Song song) {
        System.out.println("\n Questions about " + song.getTitle() + ":");
        boolean found = false;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getRelatedSong().equals(song)) {
                System.out.println((i + 1) + ". " + questions.get(i).getAnswers().size() + " answers - " + questions.get(i).getRelatedSong().getTitle());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No questions yet.");
        }
    }

    public static Question getQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }


    public boolean getDetails(Song selectedSongFromAlbum) {
        System.out.println(CYAN+"\n=== Song Details ===");
        System.out.println("Title: " + selectedSongFromAlbum.getTitle());
        System.out.println("Genre: " + selectedSongFromAlbum.getGenre());
        System.out.println("Release Date: " + selectedSongFromAlbum.getReleaseDate());
        System.out.println("Lyrics: " + selectedSongFromAlbum.getLyrics());
        System.out.println("Likes: " + selectedSongFromAlbum.getLikesCount());
        System.out.println("Dislikes: " + selectedSongFromAlbum.getDislikesCount());

        System.out.println(CYAN+"\n=== Comments ===");
        selectedSongFromAlbum.displayComments();
        return false;
    }

}