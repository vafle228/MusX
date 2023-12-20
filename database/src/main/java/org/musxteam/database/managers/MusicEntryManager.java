package org.musxteam.database.managers;

import org.musxteam.database.DatabaseConnection;
import org.musxteam.database.models.MusicEntryModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;

public class MusicEntryManager {
    private static final String ADD_COMMAND =
            "INSERT INTO MusicEntry (videoId, musicService) VALUES (\"{0}\", \"{1}\")";
    private static final String SELECT_COMMAND =
            "SELECT * FROM MusicEntry WHERE videoId = \"{0}\" and musicService = \"{1}\"";
    private static final String GET_COMMAND = "SELECT * FROM MusicEntry";
    private static final String DELETE_COMMAND = "DELETE FROM MusicEntry WHERE id = \"{0}\"";
    private static final String SELECT_ID_COMMAND = "SELECT * FROM MusicEntry WHERE id = \"{0}\"";

    public static MusicEntryModel addMusicEntry(String videoId, String musicService) {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        ArrayList<MusicEntryModel> entry = selectMusicEntry(videoId, musicService);

        if (entry.isEmpty()){
            connection.executeUpdate(MessageFormat.format(ADD_COMMAND, videoId, musicService));
            return selectMusicEntry(videoId, musicService).getFirst();
        }
        return entry.getFirst();
    }
    public static void deleteMusicEntry(int id) {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        connection.executeUpdate(MessageFormat.format(DELETE_COMMAND, id));
    }
    public static ArrayList<MusicEntryModel> selectMusicEntry(String videoId, String musicService) {
        return getMusicEntries(MessageFormat.format(SELECT_COMMAND, videoId, musicService));
    }
    public static ArrayList<MusicEntryModel> selectMusicEntry(int id) {
        return getMusicEntries(MessageFormat.format(SELECT_ID_COMMAND, id));
    }
    public static ArrayList<MusicEntryModel> getAllMusicEntries() {
        return getMusicEntries(GET_COMMAND);
    }

    private static ArrayList<MusicEntryModel> getMusicEntries(String query) {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        try {
            ArrayList<MusicEntryModel> result = new ArrayList<>();
            ResultSet resultSet = connection.executeQuery(query);

            while(resultSet.next()) {
                result.add(new MusicEntryModel(
                        resultSet.getInt("id"),
                        resultSet.getString("videoId"),
                        resultSet.getString("musicService")
                ));
            }
            resultSet.close(); return result;
        }
        catch (SQLException ex) { return new ArrayList<>(); }
    }
}
