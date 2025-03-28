package org.NowruzProject;

import org.NowruzProject.Accounts.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.NowruzProject.Dashboards.AdminDashboard;
import org.NowruzProject.Dashboards.ArtistDashboard;
import org.NowruzProject.Dashboards.Dashboard;
import org.NowruzProject.Dashboards.UserDashboard;
import org.NowruzProject.Music.Album;
import org.NowruzProject.Music.Song;

public class Main {

    static List<Artist> artists = new ArrayList<>();
    static List<Song> songs = new ArrayList<>();
    static List<Album> albums = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();
        Account loggedInAccount = null;

        while (true) {
            System.out.println("\nWelcome! Choose an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Register
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter full name: ");
                    String fullName = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter account type (user/artist/admin): ");
                    String accountType = scanner.nextLine();

                    boolean registered = accountManager.register(username, password, email, fullName, age, accountType);
                    if (registered) {
                        System.out.println("Registration successful! You can now log in.");
                    }
                    break;

                case 2: // Login
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();

                    loggedInAccount = accountManager.login(loginUsername, loginPassword);
                    if (loggedInAccount != null) {
                        System.out.println("Login successful!");


                        Dashboard dashboard = createDashboard(loggedInAccount);

                        // check dashboard
                        if (dashboard != null) {
                            dashboard.start(); // start show dashboard
                        } else {
                            System.out.println("Error: Invalid account type or dashboard creation failed.");
                        }
                    } else {
                        System.out.println("Invalid username or password!");
                    }
                    break;

                case 3: // Exit
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static Dashboard createDashboard(Account account) {
        if (account instanceof User) {
            return new UserDashboard((User) account , artists, songs, albums);
        } else if (account instanceof Artist) {
            return new ArtistDashboard((Artist) account);
        } else if (account instanceof Admin) {
            return new AdminDashboard((Admin) account);
        }
        return null;
    }
}
