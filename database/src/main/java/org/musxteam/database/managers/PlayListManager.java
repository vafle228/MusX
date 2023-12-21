package org.musxteam.database.managers;

import org.musxteam.database.DatabaseConnection;
import org.musxteam.database.models.MusicEntryModel;
import org.musxteam.database.models.PlayListModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;

public class PlayListManager {
    private static final String ADD_COMMAND =
            "INSERT INTO PlayList (title, user) VALUES (\"{0}\", \"{1}\")";
    private static final String ADD_MUSIC_COMMAND =
            "INSERT INTO playlist_music_entries (playlist_id, music_entry_id) VALUES (\"{0}\", \"{1}\")";
    private static final String SELECT_USER_MUSIC_COMMAND =
            "SELECT * FROM playlist_music_entries WHERE playlist_id = \"{0}\"";
    private static final String SELECT_MUSIC_COMMAND =
            "SELECT * FROM playlist_music_entries WHERE playlist_id = \"{0}\" and music_entry_id = \"{1}\"";
    private static final String GET_COMMAND = "SELECT * FROM PlayList WHERE user = \"{0}\"";
    private static final String DELETE_COMMAND = "DELETE FROM PlayList WHERE id = \"{0}\"";
    private static final String SELECT_COMMAND = "SELECT * FROM PlayList WHERE id = \"{0}\"";
    private static final String UPDATE_COMMAND = "UPDATE PlayList SET name = \"{0}\" WHERE id = \"{1}\"";

    public static void addPlaylist(String title, int user_id) {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        connection.executeUpdate(MessageFormat.format(ADD_COMMAND, title, user_id));
    }
    public static void deletePlaylist(int id) {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        connection.executeUpdate(MessageFormat.format(DELETE_COMMAND, id));
    }
    public static void addMusicEntry(int playlistId, String videoId,
                                     String musicService, String title, String channelTitle)
            throws IllegalArgumentException {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        MusicEntryModel entry = MusicEntryManager.addMusicEntry(videoId, musicService, title, channelTitle);

        try {
            ResultSet playlistMusic = connection.executeQuery(
                    MessageFormat.format(SELECT_MUSIC_COMMAND, playlistId, entry.id())
            );
            boolean alreadyAdded = playlistMusic.next(); playlistMusic.close();
            if (alreadyAdded) { throw new IllegalArgumentException("Already in playlist!"); }
        }
        catch (SQLException ex) { ex.printStackTrace(System.out); }
        connection.executeUpdate(MessageFormat.format(ADD_MUSIC_COMMAND, playlistId, entry.id()));
    }
    public static ArrayList<PlayListModel> selectPlaylist(int id) {
        return getPlayLists(MessageFormat.format(SELECT_COMMAND, id));
    }
    public static ArrayList<PlayListModel> getAllUserPlaylists(int userId) {
        return getPlayLists(MessageFormat.format(GET_COMMAND, userId));
    }

    private static ArrayList<PlayListModel> getPlayLists(String query) {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        try {
            ArrayList<PlayListModel> result = new ArrayList<>();
            ResultSet listSet = connection.executeQuery(query);

            while(listSet.next()) {
                result.add(new PlayListModel(
                        listSet.getInt("id"),
                        listSet.getString("title"),
                        selectMusicEntries(listSet.getInt("id"))
                ));
            }
            listSet.close(); return result;
        }
        catch (SQLException ex) { return new ArrayList<>(); }
    }
    private static ArrayList<MusicEntryModel> selectMusicEntries(int playlistId) throws SQLException {
        DatabaseConnection connection = DatabaseConnection.getInstance();

        ArrayList<MusicEntryModel> musicEntries = new ArrayList<>();
        ResultSet musicSet = connection.executeQuery(MessageFormat.format(SELECT_USER_MUSIC_COMMAND, playlistId));

        ArrayList<Integer> entriesId = new ArrayList<>();
        while (musicSet.next()) entriesId.add(musicSet.getInt("music_entry_id"));

        for (int entry_id : entriesId) {
            ArrayList<MusicEntryModel> entry = MusicEntryManager.selectMusicEntry(entry_id);
            if (!entry.isEmpty()) musicEntries.add(entry.getFirst());
        }

        musicSet.close(); return musicEntries;
    }
}
