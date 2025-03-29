package org.NowruzProject;
import org.NowruzProject.Accounts.Artist;
import org.NowruzProject.Music.Album;
import org.NowruzProject.Music.MusicManager;
import org.NowruzProject.Music.Song;

import java.util.ArrayList;
import java.util.List;

public class Search {
    private List<Artist> artists;
    private List<Song> songs;
    private List<Album> albums;

    public Search(List<Artist> artists, List<Song> songs, List<Album> albums) {
        this.artists = artists;
        this.songs = songs;
        this.albums = albums;
    }

    // Search for artists by name
    public static Song searchSongByTitle(String title) {
        return MusicManager.findSongByTitle(title);
    }

    // Search for songs by title or artist name
    public static List<Song> searchSongsByArtist(String artistName) {
        return MusicManager.findSongsByArtist(artistName);
    }
}
