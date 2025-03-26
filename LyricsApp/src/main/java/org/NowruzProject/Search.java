package org.NowruzProject;
import org.NowruzProject.Accounts.Artist;
import org.NowruzProject.Music.Album;
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
    public List<Artist> searchArtists(String query) {
        List<Artist> result = new ArrayList<>();
        for (Artist artist : artists) {
            if (artist.getFullName().toLowerCase().contains(query.toLowerCase())) {
                result.add(artist);
            }
        }
        return result;
    }
    // Search for songs by title or artist name
    public List<Song> searchSongs(String query) {
        List<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    song.getArtist().getFullName().toLowerCase().contains(query.toLowerCase())) {
                result.add(song);
            }
        }
        return result;
    }
}
