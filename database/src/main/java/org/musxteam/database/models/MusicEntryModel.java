package org.musxteam.database.models;


public record MusicEntryModel(int id, String title, String videoId, String channelTitle, String musicService) {
    public String getThumbnailUrl() { return "https://i.ytimg.com/vi/" + videoId + "/mqdefault.jpg"; }
}
