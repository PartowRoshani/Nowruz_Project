package org.NowruzProject.Dashboards;

import org.NowruzProject.Accounts.User;
import org.NowruzProject.Accounts.Artist;
import org.NowruzProject.Comments.Comment;
import org.NowruzProject.Music.MusicManager;
import org.NowruzProject.Music.Song;
import org.NowruzProject.Music.Album;
import org.NowruzProject.Search;

import java.util.ArrayList;
import java.util.List;

public class UserDashboard extends Dashboard {
    private User user;
    private Search search;
    private List<Song> songs; // list of songs

    public UserDashboard(User user, List<Artist> artists, List<Song> songs, List<Album> albums) {
        super(user);
        this.user = user;
        this.songs = songs;
        this.search = new Search(artists, songs, albums);
    }

    @Override
    public void showMenu() {
        System.out.println("\n=== User Dashboard ===");
        System.out.println("1. Follow an Artist");
        System.out.println("2. View Followed Artists");
        System.out.println("3. View Suggested Songs");
        System.out.println("4. View Lyrics of a Song");
        System.out.println("5. Request Lyrics Edit");
        System.out.println("6. Add Comment to Song");
        System.out.println("7. Like a Song");
        System.out.println("8. Dislike a Song");
        System.out.println("9. Search for Artists");
        System.out.println("10. Search for Songs");
        System.out.println("11. View all songs in the app");
        System.out.println("12. Your Profile");
        System.out.println("13. New Music");
        System.out.println("14. Top 5 Music");
        System.out.println("15. Logout");
        System.out.print("Choose an option: ");
    }

    @Override
    protected boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.print("Enter the username of the artist you want to follow: ");
                String artistUsername = scanner.nextLine();
                Artist artistToFollow = MusicManager.getArtistByUsername(artistUsername);
                user.followArtist(artistToFollow);
                return true;
            case 2:
                user.showFollowingArtists();
                return true;
            case 3:
                user.showSuggestions();
                return true;
            case 4:
                viewLyricsOfSong();
                return true;
            case 5:
                requestLyricsEdit();
                return true;
            case 6:
                addCommentToSong();
                return true;
            case 7:
                likeSong();
                return true;
            case 8:
                dislikeSong();
                return true;
            case 9:
                searchByArtist();
                return true;
            case 10:
                searchBySongTitle();
                return true;
            case 11:
                showAllSongs();
                return true;
            case 12:
                user.displayAccountInfo();
                return true;
            case 13:
                user.showNewSongsFromFollowedArtists();
                return true;
            case 14:
                showTop5Songs();
                return true;
            case 15:
                System.out.println("Logging out...");
                return false;
            default:
                System.out.println("Invalid choice. Try again.");
                return true;
        }
    }

    public void showTop5Songs() {
        List<Song> allSongs = new ArrayList<>();

        //all songs from all artists
        for (Artist artist : Artist.getAllArtists()) {
            allSongs.addAll(artist.getSongs());
        }

        // sorting by View
        allSongs.sort((song1, song2) -> Integer.compare(song2.getViewsCount(), song1.getViewsCount()));

        // Show top 5 music
        System.out.println("\n=== Top 5 Most Viewed Songs ===");
        int count = Math.min(5, allSongs.size()); // if we have less music
        for (int i = 0; i < count; i++) {
            Song song = allSongs.get(i);
            System.out.println((i + 1) + ". " + song.getTitle() + " by " + song.getArtist().getUsername() + " (Views: " + song.getViewsCount() + ")");
        }
    }


    private void viewLyricsOfSong() {
        System.out.print("Enter song title: ");
        String songTitle = scanner.nextLine();
        Song song = findSongByTitle(songTitle);
        if (song != null) {
            user.viewLyrics(song);
        } else {
            System.out.println("Song not found.");
        }
    }

    private void requestLyricsEdit() {
        System.out.print("Enter song title: ");
        String songTitle = scanner.nextLine();
        Song song = findSongByTitle(songTitle);
        if (song != null) {
            System.out.print("Enter new lyrics: ");
            String newLyrics = scanner.nextLine();
            user.requestLyricsEdit(song, newLyrics);
        } else {
            System.out.println("Song not found.");
        }
    }

    private void addCommentToSong() {
        System.out.print("Enter song title: ");
        String songTitle = scanner.nextLine();
        Song song = findSongByTitle(songTitle);
        if (song != null) {
            System.out.print("Enter your comment: ");
            String comment = scanner.nextLine();
            user.commentOnSong(song, comment);
        } else {
            System.out.println("Song not found.");
        }
    }

    private void likeSong() {
        System.out.print("Enter song title: ");
        String songTitle = scanner.nextLine();
        Song song = findSongByTitle(songTitle);
        if (song != null) {
            user.likeSong(song);
        } else {
            System.out.println("Song not found.");
        }
    }

    private void dislikeSong() {
        System.out.print("Enter song title: ");
        String songTitle = scanner.nextLine();
        Song song = findSongByTitle(songTitle);
        if (song != null) {
            user.dislikeSong(song);
        } else {
            System.out.println("Song not found.");
        }
    }

    private void searchByArtist() {
        System.out.print("Enter artist name: ");
        String artistName = scanner.nextLine();
        List<Song> songs = MusicManager.findSongsByArtist(artistName);

        if (songs.isEmpty()) {
            System.out.println("No songs found for this artist.");
        } else {
            System.out.println("\n=== Songs by " + artistName + " ===");
            for (int i = 0; i < songs.size(); i++) {
                System.out.println((i + 1) + ". " + songs.get(i).getTitle());
            }

            System.out.print("Enter the number of a song to view details (or 0 to exit): ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice <= songs.size()) {
                Song selectedSong = songs.get(choice - 1);
                selectedSong.increaseViewCount();
                System.out.println("\n=== Song Details ===");
                System.out.println("Title: " + selectedSong.getTitle());
                System.out.println("Artist: " + selectedSong.getArtist().getUsername());
                System.out.println("Release Date: " + selectedSong.getReleaseDate());
                System.out.println("Genre: " + selectedSong.getGenre());
                System.out.println("Views: " + selectedSong.getViewsCount());
                System.out.println("Lyrics: " + selectedSong.getLyrics());
                System.out.println(("like: "+selectedSong.getLikesCount()));
                System.out.println(("dislike: "+selectedSong.getDislikesCount()));
                System.out.println("\n=== Comments ===");
                selectedSong.displayComments();

                //loop for like/dislike comments
                while (true) {
                    System.out.print("Do you want to like/dislike a comment? (yes/no): ");
                    String response = scanner.nextLine().trim().toLowerCase();

                    if (response.equals("no")) {
                        break;
                    } else if (response.equals("yes")) {
                        System.out.println("\n=== Comments ===");
                        selectedSong.displayComments(); // show comments after like/dislike

                        System.out.print("Enter the number of the comment: ");
                        int commentIndex = Integer.parseInt(scanner.nextLine()) - 1;

                        if (commentIndex >= 0 && commentIndex < selectedSong.getComments().size()) {
                            System.out.print("Do you want to like or dislike? (like/dislike): ");
                            String action = scanner.nextLine().trim().toLowerCase();

                            Comment comment = selectedSong.getComments().get(commentIndex);

                            if (action.equals("like")) {
                                comment.like(user);
                                System.out.println("You liked the comment.");
                            } else if (action.equals("dislike")) {
                                comment.dislike(user);
                                System.out.println("You disliked the comment.");
                            } else {
                                System.out.println("Invalid choice. Try again.");
                            }

                            // Show updated comments
                            System.out.println("\nUpdated Comments:");
                            selectedSong.displayComments();
                        } else {
                            System.out.println("Invalid comment number. Try again.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    }
                }


            }
        }
    }

    private void searchBySongTitle() {
        System.out.print("Enter song title to search: ");
        String title = scanner.nextLine();
        Song song = MusicManager.findSongByTitle(title);

        if (song == null) {
            System.out.println("No song found with this title.");
        } else {
            song.increaseViewCount();
            System.out.println("\n=== Song Details ===");
            System.out.println("Title: " + song.getTitle());
            System.out.println("Artist: " + song.getArtist().getUsername());
            System.out.println("Release Date: " + song.getReleaseDate());
            System.out.println("Genre: " + song.getGenre());
            System.out.println("Views: " + song.getViewsCount());
            System.out.println("Lyrics:\n" + song.getLyrics());
            System.out.println(("like: " + song.getLikesCount()));
            System.out.println(("dislike: " + song.getDislikesCount()));
            System.out.println("\n=== Comments ===");
            song.displayComments();

            //loop for like/dislike comments
            while (true) {
                System.out.print("Do you want to like/dislike a comment? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();

                if (response.equals("no")) {
                    break;
                } else if (response.equals("yes")) {
                    System.out.println("\n=== Comments ===");
                    song.displayComments(); // show comments after like/dislike

                    System.out.print("Enter the number of the comment: ");
                    int commentIndex = Integer.parseInt(scanner.nextLine()) - 1;

                    if (commentIndex >= 0 && commentIndex < song.getComments().size()) {
                        System.out.print("Do you want to like or dislike? (like/dislike): ");
                        String action = scanner.nextLine().trim().toLowerCase();

                        Comment comment = song.getComments().get(commentIndex);

                        if (action.equals("like")) {
                            comment.like(user);
                            System.out.println("You liked the comment.");
                        } else if (action.equals("dislike")) {
                            comment.dislike(user);
                            System.out.println("You disliked the comment.");
                        } else {
                            System.out.println("Invalid choice. Try again.");
                        }

                        // Show updated comments
                        System.out.println("\nUpdated Comments:");
                        song.displayComments();
                    } else {
                        System.out.println("Invalid comment number. Try again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                }
            }
        }
    }

    private Song findSongByTitle(String title) {
        return MusicManager.findSongByTitle(title);
    }

    public void showAllSongs() {
        List<Song> songs = MusicManager.getAllSongs();
        if (songs.isEmpty()) {
            System.out.println("No songs available.");
        } else {
            System.out.println("\n=== All Songs ===");
            for (int i = 0; i < songs.size(); i++) {
                System.out.println((i + 1) + ". " + songs.get(i).getTitle() + " - " + songs.get(i).getArtist().getUsername());
            }
        }
    }
}
