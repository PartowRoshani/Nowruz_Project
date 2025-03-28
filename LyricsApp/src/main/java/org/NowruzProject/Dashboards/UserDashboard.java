package org.NowruzProject.Dashboards;

import org.NowruzProject.Accounts.Account;
import org.NowruzProject.Accounts.User;
import org.NowruzProject.Accounts.Artist;
import org.NowruzProject.Search;
import org.NowruzProject.Music.Song;
import org.NowruzProject.Music.Album;

import java.util.List;

public class UserDashboard extends Dashboard{
    private User user;
    private Search search;
    private List<Song> songs; // اضافه کردن لیست آهنگ‌ها

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
        System.out.println("11. Logout");
        System.out.print("Choose an option: ");
    }

    @Override
    protected boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.print("Enter artist's username: ");
                String artistUsername = scanner.nextLine();
                Artist artist = new Artist(artistUsername, "", "", "", 0);  // Fake artist object
                user.followArtist(artist);
                return true;
            case 2:
                user.showFollowingArtists();
                return true;
            case 3:
                user.showSuggestions();
                return true;
            case 4:
                System.out.print("Enter song title: ");
                String songTitle = scanner.nextLine();
                Song song = findSongByTitle(songTitle);
                if (song != null) {
                    user.viewLyrics(song);
                } else {
                    System.out.println("Song not found.");
                }
                return true;
            case 5:
                System.out.print("Enter song title: ");
                songTitle = scanner.nextLine();
                song = findSongByTitle(songTitle);
                if (song != null) {
                    System.out.print("Enter new lyrics: ");
                    String newLyrics = scanner.nextLine();
                    user.requestLyricsEdit(song, newLyrics);
                } else {
                    System.out.println("Song not found.");
                }
                return true;
            case 6:
                System.out.print("Enter song title: ");
                songTitle = scanner.nextLine();
                song = findSongByTitle(songTitle);
                if (song != null) {
                    System.out.print("Enter your comment: ");
                    String comment = scanner.nextLine();
                    user.commentOnSong(song, comment);
                } else {
                    System.out.println("Song not found.");
                }
                return true;
            case 7:
                System.out.print("Enter song title: ");
                songTitle = scanner.nextLine();
                song = findSongByTitle(songTitle);
                if (song != null) {
                    user.likeSong(song);
                } else {
                    System.out.println("Song not found.");
                }
                return true;
            case 8:
                System.out.print("Enter song title: ");
                songTitle = scanner.nextLine();
                song = findSongByTitle(songTitle);
                if (song != null) {
                    user.dislikeSong(song);
                } else {
                    System.out.println("Song not found.");
                }
                return true;
            case 9:
                System.out.print("Enter artist's name to search: ");
                String artistQuery = scanner.nextLine();
                List<Artist> foundArtists = search.searchArtists(artistQuery);
                if (foundArtists.isEmpty()) {
                    System.out.println("No artists found.");
                } else {
                    System.out.println("Found artists:");
                    for (Artist foundArtist : foundArtists) {
                        System.out.println(foundArtist.getFullName());
                    }
                }
                return true;
            case 10:
                System.out.print("Enter song title or artist name to search: ");
                String songQuery = scanner.nextLine();
                List<Song> foundSongs = search.searchSongs(songQuery);
                if (foundSongs.isEmpty()) {
                    System.out.println("No songs found.");
                } else {
                    System.out.println("Found songs:");
                    for (Song foundSong : foundSongs) {
                        System.out.println(foundSong.getTitle() + " by " + foundSong.getArtist().getFullName());
                    }
                }
                return true;
            case 11:
                System.out.println("Logging out...");
                return false;
            default:
                System.out.println("Invalid choice. Try again.");
                return true;
        }
    }
    private Song findSongByTitle(String title) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }
}
