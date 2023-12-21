package org.musxteam.database.models;

import java.util.ArrayList;

public record PlaylistModel(int id, String title, ArrayList<MusicEntryModel> musicEntries) {
}
