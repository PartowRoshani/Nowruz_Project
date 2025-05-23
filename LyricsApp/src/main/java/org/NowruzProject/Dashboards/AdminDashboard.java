package org.NowruzProject.Dashboards;

import org.NowruzProject.Accounts.Admin;
import org.NowruzProject.Accounts.Artist;
import org.NowruzProject.Music.MusicManager;
import org.NowruzProject.Music.Song;
import org.NowruzProject.Accounts.AccountManager;

import java.util.List;
import static org.NowruzProject.ColoredOutput.*;

public class AdminDashboard extends Dashboard {
    private final Admin admin;

    public AdminDashboard(Admin admin) {
        super(admin);
        this.admin = admin;
    }

    @Override
    public void showMenu() {

        System.out.println(BLUE+"       === Admin Dashboard ===");
        System.out.println(BLUE+"╔═════════════════════════════════════╗");
        System.out.println(BLUE+"║ 1. Approve an Artist                ║");
        System.out.println(BLUE+"╠═════════════════════════════════════╣");
        System.out.println(BLUE+"║ 2. Review Edit Requests for a Song  ║");
        System.out.println(BLUE+"╠═════════════════════════════════════╣");
        System.out.println(BLUE+"║ 3. Your Profile                     ║");
        System.out.println(BLUE+"╠═════════════════════════════════════╣");
        System.out.println(BLUE+"║ 4. Approve an Admin                 ║");
        System.out.println(BLUE+"╠═════════════════════════════════════╣");
        System.out.println(BLUE+"║ 5. Artists list for Approve         ║");
        System.out.println(BLUE+"╠═════════════════════════════════════╣");
        System.out.println(BLUE+"║ 6. Logout                           ║");
        System.out.println(BLUE+"╚═════════════════════════════════════╝");

        System.out.print(RESET+"Choose an option: ");
    }


    @Override
    protected boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                if(admin.isApproved()) {
                    System.out.print(CYAN + "Enter artist username: ");
                    String artistUsername = scanner.nextLine();
                    Artist artist = MusicManager.getArtistByUsername(artistUsername);
                    if (artist != null) {
                        artist.showArtistInfo();
                        System.out.print(CYAN + "Do you want to approve this artist? (yes/no): ");
                        String decision = scanner.nextLine().trim().toLowerCase();
                        admin.approveArtist(artist, decision.equals("yes"));
                        AccountManager.getPendingArtists().remove(artist);
                    } else {
                        System.out.println(RED + "Artist not found.");
                    }
                }
                else{ System.out.println(RED+"You should approve by another admin ");}
                return true;

            case 2:
                if(admin.isApproved()) {
                    System.out.print(CYAN + "Enter song title to review edit requests: ");
                    String songTitle = scanner.nextLine();
                    Song song = MusicManager.findSongByTitle(songTitle);
                    if (song != null) {
                        admin.reviewEditRequests(song);
                    } else {
                        System.out.println(RED + "Song not found.");
                    }
                }
                else{ System.out.println(RED+"You should approve by another admin ");}
                return true;
            case 3:
                admin.displayAccountInfo();
                return true;
            case 4:
                if(admin.isApproved()) {
                    List<Admin> pending = AccountManager.getPendingAdmins();
                    if (pending.isEmpty()) {
                        System.out.println(RED + "No pending admin requests.");
                        break;
                    }

                    System.out.println(YELLOW + "Pending Admin Accounts:");
                    for (int i = 0; i < pending.size(); i++) {
                        Admin a = pending.get(i);
                        System.out.println(PURPLE + (i + 1) + ". " + a.getUsername() + " - " + a.getFullName());
                    }

                    System.out.print(YELLOW + "Enter the username of the admin to approve: ");
                    String targetUsername = scanner.nextLine();
                    Admin toApprove = null;
                    for (Admin a : pending) {
                        if (a.getUsername().equals(targetUsername)) {
                            toApprove = a;
                            break;
                        }
                    }

                    if (toApprove != null) {
                        AccountManager.approveAdmin(toApprove);
                    } else {
                        System.out.println(RED + "No admin found with that username.");
                    }
                }
                else{ System.out.println(RED+"You should approve by another admin ");}
                    return true;


            case 5:
                if(admin.isApproved()) {
                    List<Artist> pendingArtists = AccountManager.getPendingArtists();
                    if (pendingArtists.isEmpty()) {
                        System.out.println(RED + "No pending artist requests.");
                    } else {
                        for (int i = 0; i < pendingArtists.size(); i++) {
                            System.out.println(PURPLE + (i + 1) + ". " + pendingArtists.get(i).getUsername());
                        }
                        System.out.print(YELLOW + "Enter the number of the artist to approve: ");
                        int artistIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (artistIndex >= 0 && artistIndex < pendingArtists.size()) {
                            AccountManager.approveArtist(pendingArtists.get(artistIndex));
                        } else {
                            System.out.println(RED + "Invalid choice.");
                        }
                    }
                }
                else{ System.out.println(RED+"You should approve by another admin ");}
                return true;
            case 6:
                System.out.println(GREEN + "Logging out...");
                return false;

            default:
                System.out.println(RED + "Invalid choice. Try again.");
                return true;
        }

        return true;
    }

}