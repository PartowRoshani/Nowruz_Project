package org.NowruzProject.Dashboards;

import org.NowruzProject.Accounts.Admin;
import org.NowruzProject.Accounts.Artist;
import org.NowruzProject.Music.MusicManager;
import org.NowruzProject.Music.Song;

public class AdminDashboard extends Dashboard {
    private final Admin admin;

    public AdminDashboard(Admin admin) {
        super(admin);
        this.admin = admin;
    }

    @Override
    public void showMenu() {
        System.out.println("\n=== Admin Dashboard ===");
        System.out.println("1. Approve an Artist");
        System.out.println("2. Review Edit Requests for a Song");
        System.out.println("3. Your Profile");
        System.out.println("4. Logout");
        System.out.print("Choose an option: ");
    }


    @Override
    protected boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.print("Enter artist username: ");
                String artistUsername = scanner.nextLine();
                Artist artist = MusicManager.getArtistByUsername(artistUsername);
                if (artist != null) {
                    artist.showArtistInfo();
                    System.out.print("Do you want to approve this artist? (yes/no): ");
                    String decision = scanner.nextLine().trim().toLowerCase();
                    admin.approveArtist(artist, decision.equals("yes"));
                } else {
                    System.out.println("Artist not found.");
                }
                return true;

            case 2:
                System.out.print("Enter song title to review edit requests: ");
                String songTitle = scanner.nextLine();
                Song song = MusicManager.findSongByTitle(songTitle);
                if (song != null) {
                    admin.reviewEditRequests(song);
                } else {
                    System.out.println("Song not found.");
                }
                return true;
            case 3:
                admin.displayAccountInfo();
                return true;
            case 4:
                System.out.println("Logging out...");
                return false;

            default:
                System.out.println("Invalid choice. Try again.");
                return true;
        }
    }

}

