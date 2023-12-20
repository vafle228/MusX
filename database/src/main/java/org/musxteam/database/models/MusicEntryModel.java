package org.musxteam.database.models;


public class MusicEntryModel {
    private final int id;
    private final String videoId;
    private final String musicService;

    public MusicEntryModel(int id, String videoId, String musicService) {
        this.id = id;
        this.videoId = videoId;
        this.musicService = musicService;
    }

    public int getId() { return id; }
    public String getVideoId() { return videoId; }
    public String getMusicService() { return musicService; }
}
