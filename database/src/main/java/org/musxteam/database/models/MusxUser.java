package org.musxteam.database.models;


import org.musxteam.music.service.MusicServiceBase;
import org.musxteam.music.service.YoutubeMusicService;

public class MusxUser {
    public String name;
    public MusicServiceBase musicService;
    private static final MusxUser instance = new MusxUser("User", new YoutubeMusicService());

    public MusxUser(String name, MusicServiceBase musicService) {
        this.name = name; this.musicService = musicService;
    }

    public static MusxUser getInstance() { return instance; }
}
