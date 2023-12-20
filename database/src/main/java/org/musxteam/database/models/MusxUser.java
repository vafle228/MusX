package org.musxteam.database.models;


import org.musxteam.music.service.MusicServiceBase;
import org.musxteam.music.service.YoutubeMusicService;

public class MusxUser {
    private final int id;
    private final String username;

    public MusicServiceBase musicService;
    private static final MusxUser instance = new MusxUser(1, "User", new YoutubeMusicService());

    public MusxUser(int id, String username, MusicServiceBase musicService) {
        this.id = id;
        this.username = username;
        this.musicService = musicService;
    }

    public static MusxUser getInstance() { return instance; }

    public int getId() { return id; }
    public String getUsername() { return username; }
}
