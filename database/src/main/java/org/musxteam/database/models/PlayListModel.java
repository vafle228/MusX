package org.musxteam.database.models;

import java.util.ArrayList;

public record PlayListModel(int id, String title, ArrayList<MusicEntryModel> musicEntries) {
}
