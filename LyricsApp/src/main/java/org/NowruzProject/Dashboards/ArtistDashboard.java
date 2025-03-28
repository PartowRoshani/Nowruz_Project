package org.NowruzProject.Dashboards;

import org.NowruzProject.Accounts.Account;
import org.NowruzProject.Accounts.User;
import org.NowruzProject.Accounts.Artist;
import org.NowruzProject.Music.Genre;
import org.NowruzProject.Search;
import org.NowruzProject.Music.Song;
import org.NowruzProject.Music.Album;

import java.util.ArrayList;

public class ArtistDashboard extends Dashboard {
    private Artist artist;

    public ArtistDashboard(Artist artist) {
        super(artist);
        this.artist = artist;
    }

    @Override
    public void showMenu() {
        System.out.println("\n=== Artist Dashboard ===");
        System.out.println("1. Add a Song");
        System.out.println("2. View My Songs");
        System.out.println("3. View My Albums");
        System.out.println("4. Add an Album");
        System.out.println("5. Logout");
        System.out.print("Choose an option: ");
    }

    @Override
    protected boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.print("Enter song title: ");
                String songTitle = scanner.nextLine();
                System.out.print("Enter release date: ");
                String releaseDate = scanner.nextLine();
                System.out.print("Enter album title (optional, press Enter to skip): ");
                String albumTitle = scanner.nextLine();


                // get the genre
                System.out.println("Choose genre for the song:");
                System.out.println("1. Pop");
                System.out.println("2. Rock");
                System.out.println("3. Hip Hop");
                System.out.println("4. Jazz");
                System.out.println("5. Classical");
                System.out.println("6. Electronic ");
                System.out.println("7. Country");
                System.out.println("8. Blues");
                System.out.println("9. Reggae");
                System.out.println("10. Metal");
                System.out.println("11. RNB");
                System.out.println("12. Folk");
                System.out.println("13. Latin");
                System.out.println("14. Soul");

                System.out.print("Enter your choice: ");
                int genreChoice = Integer.parseInt(scanner.nextLine());
                Genre genre = null;

                switch (genreChoice) {
                    case 1:
                        genre = Genre.POP;
                        break;
                    case 2:
                        genre = Genre.ROCK;
                        break;
                    case 3:
                        genre = Genre.HIP_HOP;
                        break;
                    case 4:
                        genre = Genre.JAZZ;
                        break;
                    case 5:
                        genre = Genre.CLASSICAL;
                        break;
                    case 6 :
                        genre = Genre.ELECTRONIC;
                        break;
                    case 7 :
                        genre = Genre.COUNTRY;
                        break;
                    case 8 :
                        genre = Genre.BLUES;
                        break;
                    case 9 :
                        genre = Genre.REGGAE;
                        break;
                    case 10 :
                        genre = Genre.METAL;
                        break;
                    case 11:
                        genre = Genre.RNB;
                        break;
                    case 12 :
                        genre = Genre.FOLK;
                        break;
                    case 13 :
                        genre = Genre.LATIN;
                        break;
                    case 14 :
                        genre = Genre.SOUL;
                        break;
                    default:
                        System.out.println("Invalid genre. Defaulting to Pop.");
                        genre = Genre.POP;
                        break;
                }

                // Create New album
                Album album = null;
                if (!albumTitle.isEmpty()) {
                    album = new Album(albumTitle, releaseDate, artist);
                }

                // Create new Song
                Song newSong = new Song(songTitle, artist, releaseDate, album, genre, 0, new ArrayList<>());
                artist.addSong(newSong);

                System.out.print("Enter lyrics for the song: ");
                String lyrics = scanner.nextLine();
                newSong.setLyrics(lyrics);

                System.out.println("Song with lyrics added successfully!");

                return true;
            case 2:
                artist.showSongs();
                return true;
            case 3:
                artist.showAlbums();
                return true;
            case 4:
                System.out.print("Enter album title: ");
                String newAlbumTitle = scanner.nextLine();
                System.out.print("Enter release date: ");
                String newAlbumReleaseDate = scanner.nextLine();
                artist.addAlbum(new Album(newAlbumTitle, newAlbumReleaseDate, artist));
                return true;
            case 5:
                System.out.println("Logging out...");
                return false;
            default:
                System.out.println("Invalid choice. Try again.");
                return true;
        }
    }
}