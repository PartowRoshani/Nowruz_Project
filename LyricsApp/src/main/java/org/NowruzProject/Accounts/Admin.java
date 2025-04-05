package org.NowruzProject.Accounts;

import org.NowruzProject.Accounts.AccountType;
import org.NowruzProject.Music.Song;
import org.NowruzProject.Music.Album;
import java.util.List;
import java.util.Scanner;

import static org.NowruzProject.ColoredOutput.*;

public class Admin extends Account {
    private final Scanner scanner = new Scanner(System.in);


    public Admin(String username, String password, String email, String fullName, int age) {
        super(username, password, email, fullName, age, AccountType.ADMIN);
    }

    // method for approve edit
    public void reviewEditRequests(Song song) {
        if (!song.getArtist().isActive()) {  // if artist not active
            List<String> requests = song.getEditRequests();  // Request list
            if (requests.isEmpty()) {
                System.out.println(RED+"No edit requests found for " + song.getTitle());
                return;
            }

            System.out.println(BLUE+"Edit requests for song: " + song.getTitle());
            for (int i = 0; i < requests.size(); i++) {
                System.out.println((i + 1) + ". " + requests.get(i));
            }

            System.out.print(CYAN+"Enter request number to approve or 0 to cancel: ");
            int requestChoice = Integer.parseInt(scanner.nextLine());

            if (requestChoice > 0 && requestChoice <= requests.size()) {
                String approvedLyrics = requests.get(requestChoice - 1);
                song.setLyrics(approvedLyrics);  // change lyric
                song.getEditRequests().remove(requestChoice - 1);  // remove request
                System.out.println(GREEN+"Lyrics updated successfully for: " + song.getTitle());
            }
        } else {
            System.out.println(RED+"Artist is active. Admin cannot review edit requests.");
        }
    }


    // method for get the approval from admin
    public void approveArtist(Artist artist, boolean isApproved) {
        if (isApproved) {
            artist.setApproved(true);  // approved
            System.out.println(GREEN+"Admin approved the artist: " + artist.getUsername());
        } else {
            artist.setApproved(false);  // reject
            System.out.println(RED+"Admin rejected the artist: " + artist.getUsername());
        }
    }


}