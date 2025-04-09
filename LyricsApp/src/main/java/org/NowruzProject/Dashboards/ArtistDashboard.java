package org.NowruzProject.Dashboards;

import org.NowruzProject.Accounts.Account;
import org.NowruzProject.Accounts.User;
import org.NowruzProject.Accounts.Artist;
import org.NowruzProject.AnswerAndQuestion.Answer;
import org.NowruzProject.AnswerAndQuestion.Question;
import org.NowruzProject.AnswerAndQuestion.QuestionManager;
import org.NowruzProject.Comments.Comment;
import org.NowruzProject.Music.Genre;
import org.NowruzProject.Music.MusicManager;
import org.NowruzProject.Music.Song;
import org.NowruzProject.Music.Album;

import java.util.ArrayList;
import java.util.Scanner;

import static org.NowruzProject.ColoredOutput.*;
import static org.NowruzProject.Music.MusicManager.findSongByTitle;

public class ArtistDashboard extends Dashboard {
    private Artist artist;
    private QuestionManager questionManager;


    public ArtistDashboard(Artist artist) {
        super(artist);
        this.artist = artist;
    }

    @Override
    public void showMenu() {
        System.out.println(BLUE+"       === Artist Dashboard ===");
        System.out.println(BLUE+"╔═════════════════════════════════════╗");
        System.out.println(BLUE+"║ 1. Add a Song                       ║");
        System.out.println(BLUE+"╠═════════════════════════════════════╣");
        System.out.println(BLUE+"║ 2. View My Songs                    ║");
        System.out.println(BLUE+"╠═════════════════════════════════════╣");
        System.out.println(BLUE+"║ 3. View My Albums                   ║");
        System.out.println(BLUE+"╠═════════════════════════════════════╣");
        System.out.println(BLUE+"║ 4. Add an Album                     ║");
        System.out.println(BLUE+"╠═════════════════════════════════════╣");
        System.out.println(BLUE+"║ 5. Remove a Song                    ║");
        System.out.println(BLUE+"╠═════════════════════════════════════╣");
        System.out.println(BLUE+"║ 6. Remove an Album                  ║");
        System.out.println(BLUE+"╠═════════════════════════════════════╣");
        System.out.println(BLUE+"║ 7. Edit Song Lyrics                 ║");
        System.out.println(BLUE+"╠═════════════════════════════════════╣");
        System.out.println(BLUE+"║ 8. Set Active Status                ║");
        System.out.println(BLUE+"╠═════════════════════════════════════╣");
        System.out.println(BLUE+"║ 9. View Lyrics Change Requests      ║");
        System.out.println(BLUE+"╠═════════════════════════════════════╣");
        System.out.println(BLUE+"║ 10. Your Profile                    ║");
        System.out.println(BLUE+"╠═════════════════════════════════════╣");
        System.out.println(BLUE+"║ 11. Answer a Question about a Song  ║");
        System.out.println(BLUE+"╠═════════════════════════════════════╣");
        System.out.println(BLUE+"║ 12. Logout                          ║");
        System.out.println(BLUE+"╚═════════════════════════════════════╝");
        System.out.print(RESET+"Choose an option: ");
    }

    @Override
    protected boolean handleChoice(int choice) {
        switch (choice) {
            case 1:

                String songTitle;
                if (artist.isApproved()) {
                    System.out.print(YELLOW+"Enter song title: ");
                    songTitle = scanner.nextLine();
                    System.out.print(YELLOW+"Enter release date: ");
                    String releaseDate = scanner.nextLine();
                    System.out.print(YELLOW+"Enter album title (optional, press Enter to skip): ");
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
                    System.out.println(YELLOW+"Choose genre for the song:");
                    System.out.println(PURPLE+"╔══════════════╗");
                    System.out.println(PURPLE+"║ 1. Pop       ║");
                    System.out.println(PURPLE+"╠══════════════╣");
                    System.out.println(PURPLE+"║ 2. Rock      ║");
                    System.out.println(PURPLE+"╠══════════════╣");
                    System.out.println(PURPLE+"║ 3. Hip Hop   ║");
                    System.out.println(PURPLE+"╠══════════════╣");
                    System.out.println(PURPLE+"║ 4. Jazz      ║");
                    System.out.println(PURPLE+"╠══════════════╣");
                    System.out.println(PURPLE+"║ 5. Classical ║");
                    System.out.println(PURPLE+"╠══════════════╣");
                    System.out.println(PURPLE+"║ 6. Electronic║");
                    System.out.println(PURPLE+"╠══════════════╣");
                    System.out.println(PURPLE+"║ 7. Country   ║");
                    System.out.println(PURPLE+"╠══════════════╣");
                    System.out.println(PURPLE+"║ 8. Blues     ║");
                    System.out.println(PURPLE+"╠══════════════╣");
                    System.out.println(PURPLE+"║ 9. Reggae    ║");
                    System.out.println(PURPLE+"╠══════════════╣");
                    System.out.println(PURPLE+"║ 10. Metal    ║");
                    System.out.println(PURPLE+"╠══════════════╣");
                    System.out.println(PURPLE+"║ 11. RNB      ║");
                    System.out.println(PURPLE+"╠══════════════╣");
                    System.out.println(PURPLE+"║ 12. Folk     ║");
                    System.out.println(PURPLE+"╠══════════════╣");
                    System.out.println(PURPLE+"║ 13. Latin    ║");
                    System.out.println(PURPLE+"╠══════════════╣");
                    System.out.println(PURPLE+"║ 14. Soul     ║");
                    System.out.println(PURPLE+"╚══════════════╝");

                    System.out.print(YELLOW+"Enter your choice: ");
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
                        case 6:
                            genre = Genre.ELECTRONIC;
                            break;
                        case 7:
                            genre = Genre.COUNTRY;
                            break;
                        case 8:
                            genre = Genre.BLUES;
                            break;
                        case 9:
                            genre = Genre.REGGAE;
                            break;
                        case 10:
                            genre = Genre.METAL;
                            break;
                        case 11:
                            genre = Genre.RNB;
                            break;
                        case 12:
                            genre = Genre.FOLK;
                            break;
                        case 13:
                            genre = Genre.LATIN;
                            break;
                        case 14:
                            genre = Genre.SOUL;
                            break;
                        default:
                            System.out.println(RED+"Invalid genre. Defaulting to Pop.");
                            genre = Genre.POP;
                            break;
                    }


                    // Create new Song
                    Song newSong = new Song(songTitle, artist, releaseDate, album, genre, 0);
                    artist.addSong(newSong);
                    MusicManager.addSong(newSong);

                    System.out.print(YELLOW+"Enter lyrics for the song: ");
                    String lyrics = scanner.nextLine();
                    newSong.setLyrics(lyrics);
                    System.out.println(GREEN+"Lyric Add Successfully");
                    System.out.println("Song Add Successfully");
                    // Ask if the artist wants to add collaborators to the song
                    System.out.print(YELLOW+"Would you like to add a collaborator to this song? (yes/no): ");
                    String collaboratorChoice = scanner.nextLine().trim().toLowerCase();
                    if (collaboratorChoice.equals("yes")) {
                        System.out.print(YELLOW+"Enter collaborator's username: ");
                        String collaboratorUsername = scanner.nextLine();

                        // Check if the collaborator exists in the system
                        Artist collaborator = MusicManager.getArtistByUsername(collaboratorUsername);
                        if (collaborator != null) {
                            // Add the collaborator to the song's list of artists
                            newSong.addCollaborator(collaborator);

                            // Add the song to the collaborator's list of songs

                            System.out.println(GREEN+"Collaborator added successfully! Song also added to their list.");
                        } else {
                            System.out.println(RED+"Collaborator not found in the system.");
                        }
                    }

                } else {
                    System.out.println(RED+"You must be approved by an admin to release Song.");
                }

                return true;
            case 2:
                artist.showSongs(); //Songs list
                Song selectedSong;
                if (!artist.getSongs().isEmpty()) { // if song is available
                    System.out.print(YELLOW+"Enter the song title to see details (or press Enter to skip): ");
                    String selectedSongTitle = scanner.nextLine().trim();

                    if (!selectedSongTitle.isEmpty()) {
                        selectedSong = artist.findSong(selectedSongTitle);
                        if (selectedSong != null) {
                            System.out.println(CYAN+"\n=== Song Details ===");
                            System.out.println(CYAN+"Title: " + selectedSong.getTitle());
                            System.out.println(CYAN+"Album: " + (selectedSong.getAlbum() != null ? selectedSong.getAlbum().getTitle() : "No album"));
                            System.out.println(CYAN+"Genre: " + selectedSong.getGenre());
                            System.out.println(CYAN+"Release Date: " + selectedSong.getReleaseDate());
                            System.out.println(CYAN+"Likes: " + selectedSong.getLikesCount());
                            System.out.println(CYAN+"Dislikes: " + selectedSong.getDislikesCount());

                            System.out.println(CYAN+"\n=== Comments ===");
                            selectedSong.displayComments();


                        }
                    } else {
                        System.out.println(RED+"Song not found.");
                    }
                }
                return true;

            case 3:
                artist.viewAlbums();
                return true;
            case 4:
                if (artist.isApproved()) {
                    System.out.print(YELLOW+"Enter album title: ");
                    String newAlbumTitle = scanner.nextLine();
                    System.out.print(YELLOW+"Enter release date: ");
                    String newAlbumReleaseDate = scanner.nextLine();
                    artist.addAlbum(new Album(newAlbumTitle, newAlbumReleaseDate, artist));
                } else {
                    System.out.println(RED+"You must be approved by an admin to release albums.");
                }
                return true;
            case 5:
                System.out.print(YELLOW+"Enter song title to remove: ");
                String removeSongTitle = scanner.nextLine();
                Song songToRemove = artist.findSong(removeSongTitle);
                if (songToRemove != null) {
                    artist.removeSong(songToRemove);
                } else {
                    System.out.println(RED+"Song not found.");
                }
                return true;
            case 6:
                System.out.print(YELLOW+"Enter album title to remove: ");
                String removeAlbumTitle = scanner.nextLine();
                artist.removeAlbum(removeAlbumTitle);
                return true;
            case 7:
                System.out.print(YELLOW+"Enter song title to edit: ");
                String editSongTitle = scanner.nextLine();
                Song songToEdit = artist.findSong(editSongTitle);
                if (songToEdit != null) {
                    System.out.print(YELLOW+"Enter new lyrics: ");
                    String newLyrics = scanner.nextLine();
                    artist.editSong(songToEdit, newLyrics);
                } else {
                    System.out.println(RED+"Song not found.");
                }
                return true;
            case 8:
                System.out.print(YELLOW+"Set active status (true/false): ");
                boolean isActive = Boolean.parseBoolean(scanner.nextLine());
                artist.setActive(isActive);
                System.out.println(GREEN+"Active status updated.");
                return true;
            case 9:
                System.out.print(YELLOW+"Enter song title to view change requests: ");
                String songTitle1 = scanner.nextLine();
                Song song = artist.findSong(songTitle1);

                if (song != null && !song.getEditRequests().isEmpty()) {
                    System.out.println(GREEN+"Change requests for " + song.getTitle() + ":");
                    for (int i = 0; i < song.getEditRequests().size(); i++) {
                        System.out.println((i + 1) + ". " + song.getEditRequests().get(i));
                    }

                    System.out.print(YELLOW+"Enter the request number to approve/reject or 0 to go back: ");
                    int requestChoice = Integer.parseInt(scanner.nextLine());

                    if (requestChoice > 0 && requestChoice <= song.getEditRequests().size()) {
                        System.out.print(YELLOW+"Approve change? (yes/no): ");
                        String approval = scanner.nextLine();

                        // Get the suggested lyrics and send to handle request
                        String editRequestText = song.getEditRequests().get(requestChoice - 1);
                        artist.handleEditRequest(song, editRequestText, approval.equalsIgnoreCase("yes"));
                    }
                } else {
                    System.out.println(RED+"No change requests found.");
                }
                return true;

            case 10:
                artist.showArtistInfo();
                return true;
            case 11:
                System.out.print(PURPLE+"Enter song title: ");
                songTitle = scanner.nextLine();
                selectedSong = findSongByTitle(songTitle);
                if (selectedSong != null) {
                    Song.displayQuestionsForSong(selectedSong);
                    System.out.print(PURPLE+"Enter the number of a question to view details (or 0 to exit): ");
                    int questionIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (questionIndex >= 0) {
                        Question selectedQuestion = Song.getQuestion(questionIndex);
                        if (selectedQuestion != null) {
                            selectedQuestion.displayQuestion();
                            //answer
                            System.out.print(YELLOW+"Enter your answer: ");
                            String answerText = scanner.nextLine();

                            //check is answer null or not
                            if (answerText.trim().isEmpty()) {
                                System.out.println(RED+"Answer cannot be empty.");
                                return true;
                            }
                            //add answer to question
                            Answer answer = new Answer(answerText, artist);
                            selectedQuestion.addAnswer(answer);
                            System.out.println(GREEN+"Your answer has been added!");

                        } else {
                            System.out.println(RED+"Invalid question number.");
                        }
                    }
                } else {
                    System.out.println(RED+"Song not found.");
                }
                return true;
            case 12:
                System.out.println(GREEN+"Logging out...");
                return false;

            default:
                System.out.println(RED+"Invalid choice. Try again.");
                return true;
        }
    }
}