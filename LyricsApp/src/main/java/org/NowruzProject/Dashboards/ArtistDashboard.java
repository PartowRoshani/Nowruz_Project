package org.NowruzProject.Dashboards;

import org.NowruzProject.Accounts.Account;
import org.NowruzProject.Accounts.User;
import org.NowruzProject.Accounts.Artist;
import org.NowruzProject.Comments.Comment;
import org.NowruzProject.Music.Genre;
import org.NowruzProject.Music.MusicManager;
import org.NowruzProject.Search;
import org.NowruzProject.Music.Song;
import org.NowruzProject.Music.Album;

import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.println("5. Remove a Song");
        System.out.println("6. Remove an Album");
        System.out.println("7. Edit Song Lyrics");
        System.out.println("8. Set Active Status");
        System.out.println("9. View Lyrics Change Requests");
        System.out.println("10. Your Profile");
        System.out.println("11. Logout");
        System.out.print("Choose an option: ");
    }

    @Override
    protected boolean handleChoice(int choice) {
        switch (choice) {
            case 1:

                if(artist.isApproved()) {
                    System.out.println("Song with lyrics added successfully!");
                    System.out.print("Enter song title: ");
                    String songTitle = scanner.nextLine();
                    System.out.print("Enter release date: ");
                    String releaseDate = scanner.nextLine();
                    System.out.print("Enter album title (optional, press Enter to skip): ");
                    String albumTitle = scanner.nextLine();
                    Album album = null;

                    if (!albumTitle.isEmpty()) {
                        // Search in album list
                        for (Album existingAlbum : artist.getAlbums()) {
                            if (existingAlbum.getTitle().equalsIgnoreCase(albumTitle)) {
                                album = existingAlbum;
                                break;
                            }
                        }

                        // if the album not find
                        if (album == null) {
                            album = new Album(albumTitle, releaseDate, artist);
                            artist.addAlbum(album);
                        }
                    }


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


                    // Create new Song
                    Song newSong = new Song(songTitle, artist, releaseDate, album, genre, 0, new ArrayList<>());
                    artist.addSong(newSong);
                    MusicManager.addSong(newSong);

                    System.out.print("Enter lyrics for the song: ");
                    String lyrics = scanner.nextLine();
                    newSong.setLyrics(lyrics);
                    // Ask if the artist wants to add collaborators to the song
                    System.out.print("Would you like to add a collaborator to this song? (yes/no): ");
                    String collaboratorChoice = scanner.nextLine().trim().toLowerCase();
                    if (collaboratorChoice.equals("yes")) {
                        System.out.print("Enter collaborator's username: ");
                        String collaboratorUsername = scanner.nextLine();

                        // Check if the collaborator exists in the system
                        Artist collaborator = MusicManager.getArtistByUsername(collaboratorUsername);
                        if (collaborator != null) {
                            // Add the collaborator to the song's list of artists
                            newSong.addCollaborator(collaborator);

                            // Add the song to the collaborator's list of songs
                            collaborator.addSong(newSong);

                            System.out.println("Collaborator added successfully! Song also added to their list.");
                        } else {
                            System.out.println("Collaborator not found in the system.");
                        }
                    }

                }
                else {
                    System.out.println("You must be approved by an admin to release Song.");
                }

                return true;
            case 2:
                artist.showSongs(); //Songs list
                if (!artist.getSongs().isEmpty()) { // if song is available
                    System.out.print("Enter the song title to see details (or press Enter to skip): ");
                    String selectedSongTitle = scanner.nextLine().trim();

                    if (!selectedSongTitle.isEmpty()) {
                        Song selectedSong = artist.findSong(selectedSongTitle);
                        if (selectedSong != null) {
                            System.out.println("\n=== Song Details ===");
                            System.out.println("Title: " + selectedSong.getTitle());
                            System.out.println("Album: " + (selectedSong.getAlbum() != null ? selectedSong.getAlbum().getTitle() : "No album"));
                            System.out.println("Genre: " + selectedSong.getGenre());
                            System.out.println("Release Date: " + selectedSong.getReleaseDate());
                            System.out.println("Likes: " + selectedSong.getLikesCount());
                            System.out.println("Dislikes: " + selectedSong.getDislikesCount());

                            System.out.println("\n=== Comments ===");
                            selectedSong.displayComments();


                            }
                        } else {
                            System.out.println("Song not found.");
                    }
                }
                return true;

            case 3:
                artist.viewAlbums();
                return true;
            case 4:
               if (artist.isApproved())
               {
                   System.out.print("Enter album title: ");
                   String newAlbumTitle = scanner.nextLine();
                   System.out.print("Enter release date: ");
                   String newAlbumReleaseDate = scanner.nextLine();
                   artist.addAlbum(new Album(newAlbumTitle, newAlbumReleaseDate, artist));
               }
               else{
                   System.out.println("You must be approved by an admin to release albums.");
               }
                return true;
            case 5 :
                System.out.print("Enter song title to remove: ");
                String removeSongTitle = scanner.nextLine();
                Song songToRemove = artist.findSong(removeSongTitle);
                if (songToRemove != null) {
                    artist.removeSong(songToRemove);
                } else {
                    System.out.println("Song not found.");
                }
                return true;
            case 6:
                System.out.print("Enter album title to remove: ");
                String removeAlbumTitle = scanner.nextLine();
                artist.removeAlbum(removeAlbumTitle);
                return true;
            case 7 :
                System.out.print("Enter song title to edit: ");
                String editSongTitle = scanner.nextLine();
                Song songToEdit = artist.findSong(editSongTitle);
                if (songToEdit != null) {
                    System.out.print("Enter new lyrics: ");
                    String newLyrics = scanner.nextLine();
                    artist.editSong(songToEdit, newLyrics);
                } else {
                    System.out.println("Song not found.");
                }
                return true;
            case 8:
                System.out.print("Set active status (true/false): ");
                boolean isActive = Boolean.parseBoolean(scanner.nextLine());
                artist.setActive(isActive);
                System.out.println("Active status updated.");
                return true;
            case 9:
                System.out.print("Enter song title to view change requests: ");
                String songTitle1 = scanner.nextLine();
                Song song = artist.findSong(songTitle1);

                if (song != null && !song.getEditRequests().isEmpty()) {
                    System.out.println("Change requests for " + song.getTitle() + ":");
                    for (int i = 0; i < song.getEditRequests().size(); i++) {
                        System.out.println((i + 1) + ". " + song.getEditRequests().get(i));
                    }

                    System.out.print("Enter the request number to approve/reject or 0 to go back: ");
                    int requestChoice = Integer.parseInt(scanner.nextLine());

                    if (requestChoice > 0 && requestChoice <= song.getEditRequests().size()) {
                        System.out.print("Approve change? (yes/no): ");
                        String approval = scanner.nextLine();

                        // Get the suggested lyrics and send to handle request
                        String editRequestText = song.getEditRequests().get(requestChoice - 1);
                        artist.handleEditRequest(song, editRequestText, approval.equalsIgnoreCase("yes"));
                    }
                } else {
                    System.out.println("No change requests found.");
                }
                return true;

            case 10:
                artist.showArtistInfo();
                return true;
            case 11:
                System.out.println("Logging out...");
                return false;

            default:
                System.out.println("Invalid choice. Try again.");
                return true;
        }
    }
}