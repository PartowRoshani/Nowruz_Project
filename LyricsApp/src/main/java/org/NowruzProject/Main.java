package org.NowruzProject;

import org.NowruzProject.Accounts.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.NowruzProject.AnswerAndQuestion.QuestionManager;
import org.NowruzProject.Dashboards.AdminDashboard;
import org.NowruzProject.Dashboards.ArtistDashboard;
import org.NowruzProject.Dashboards.Dashboard;
import org.NowruzProject.Dashboards.UserDashboard;
import org.NowruzProject.Music.Album;
import org.NowruzProject.Music.Song;
import org.NowruzProject.AnswerAndQuestion.QuestionManager;

import static org.NowruzProject.ColoredOutput.*;

public class Main {

    static QuestionManager questionManager = new QuestionManager();
    static List<Artist> artists = new ArrayList<>();
    static List<Song> songs = new ArrayList<>();
    static List<Album> albums = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();
        Account loggedInAccount = null;

        while (true) {
            System.out.println(RESET+"\nWelcome! Choose an option:");
            System.out.println(PURPLE+"╔══════════════╗");
            System.out.println(PURPLE+"║ 1. Register  ║");
            System.out.println(PURPLE+"╠══════════════╣");
            System.out.println(PURPLE+"║ 2. Login     ║");
            System.out.println(PURPLE+"╠══════════════╣");
            System.out.println(PURPLE+"║ 3. Exit      ║");
            System.out.println(PURPLE+"╚══════════════╝");
            System.out.print(RESET+"Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Register
                    System.out.print(RED+"Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print(RED+"Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print(RED+"Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print(RED+"Enter full name: ");
                    String fullName = scanner.nextLine();
                    System.out.print(RED+"Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print(RED+"Enter account type (user/artist/admin): ");
                    String accountType = scanner.nextLine();

                    boolean registered = accountManager.register(username, password, email, fullName, age, accountType);
                    if (registered) {
                        System.out.println(GREEN+"Registration successful! You can now log in.");
                    }
                    break;

                case 2: // Login
                    System.out.print(RED+"Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print(RED+"Enter password: ");
                    String loginPassword = scanner.nextLine();

                    loggedInAccount = accountManager.login(loginUsername, loginPassword);
                    if (loggedInAccount != null) {
                        System.out.println(GREEN+"Login successful!");


                        Dashboard dashboard = createDashboard(loggedInAccount);

                        // check dashboard
                        if (dashboard != null) {
                            dashboard.start(); // start show dashboard
                        } else {
                            System.out.println(RED+"Error: Invalid account type or dashboard creation failed.");
                        }
                    } else {
                        System.out.println(RED+"Invalid username or password!");
                    }
                    break;

                case 3: // Exit
                    System.out.println(GREEN+"Exiting... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println(RED+"Invalid choice. Please try again.");
            }
        }
    }

    public static Dashboard createDashboard(Account account) {
        if (account instanceof User) {
            return new UserDashboard((User) account, artists, songs, albums, questionManager);
        } else if (account instanceof Artist) {
            return new ArtistDashboard((Artist) account);
        } else if (account instanceof Admin) {
            return new AdminDashboard((Admin) account);
        }
        return null;
    }
}