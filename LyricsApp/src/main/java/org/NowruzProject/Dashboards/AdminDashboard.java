package org.NowruzProject.Dashboards;

import org.NowruzProject.Accounts.Admin;
import org.NowruzProject.Accounts.Artist;
import org.NowruzProject.Music.MusicManager;
import org.NowruzProject.Music.Song;

public class AdminDashboard extends Dashboard {
    private Admin admin;

    public AdminDashboard(Admin admin) {
        super(admin);
        this.admin = admin;
    }

    @Override
    public void showMenu() {
        System.out.println("\n=== Admin Dashboard ===");
        System.out.println("1. Approve an Artist");
        System.out.println("2. Logout");
        System.out.print("Choose an option: ");
    }

    @Override
    protected boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.print("Enter artist username: ");
                String artistUsername = scanner.nextLine();

                Artist artist = MusicManager.getArtistByUsername(artistUsername); // Search for find the artist
                if (artist != null) {
                    // Artist info
                    artist.showArtistInfo();

                    // ask about accept or reject
                    System.out.print("Do you want to approve this artist? (yes/no): ");
                    String decision = scanner.nextLine().trim().toLowerCase();
                    if (decision.equals("yes")) {
                        admin.approveArtist(artist, true);
                        System.out.println("Artist approved.");
                    } else {
                        admin.approveArtist(artist, false);
                        System.out.println("Artist rejected.");
                    }
                } else {
                    System.out.println("Artist not found. Cannot approve or reject.");
                }
                return true;
            case 2:
                System.out.println("Logging out...");
                return false;
            default:
                System.out.println("Invalid choice. Try again.");
                return true;
        }
    }

}

