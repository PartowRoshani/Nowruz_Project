package org.NowruzProject.Accounts;

import org.NowruzProject.AccountType;
import org.NowruzProject.Music.Song;
import org.NowruzProject.Music.Album;
import java.util.List;

public class Admin extends Account {

    public Admin(String username, String password, String email, String fullName, int age) {
        super(username, password, email, fullName, age, AccountType.ADMIN);
    }

    // method for approve edit
    public void approveEdit(Song song, String newLyrics) {
        // if the artist isn't active the admin can edit lyrics
        if (!song.getArtist().isActive()) {
            song.setLyrics(newLyrics);
            System.out.println("Admin approved lyrics update for song: " + song.getTitle());
        } else {
            System.out.println("Song's artist is active. No need for admin approval.");
        }
    }

    // method for get the approval from admin
    public void approveArtist(Artist artist, boolean isApproved) {
        if (isApproved) {
            artist.setApproved(true);  // approved
            System.out.println("Admin approved the artist: " + artist.getUsername());
        } else {
            artist.setApproved(false);  // reject
            System.out.println("Admin rejected the artist: " + artist.getUsername());
        }
    }

    // Show information
    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("Role: Admin");
    }
}

