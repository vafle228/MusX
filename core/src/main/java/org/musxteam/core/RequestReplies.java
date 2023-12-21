package org.musxteam.core;

public enum RequestReplies {
    HELP("Hello, how can I help u?"),
    SEARCH_START("Введите поисковой запрос"),
    DOWNLOAD_START("Введите id трека"),
    ADD_PLAYLIST_START("Enter playlist name"),
    PLAYLIST_ADDED("New playlist successfully added"),
    PLAYLIST_CHOOSE("Choose playlist"),
    PLAYLIST_TRACK_ADDED("Track was successfully added to playlist!"),
    PLAYLIST_TRACK_REMOVED("Track was successfully removed from playlist!"),
    ILLEGAL_PLAYLIST_ID("Illegal playlist id! Chose another"),
    EMPTY_PLAYLIST("This playlist is empty!"),
    EMPTY_COMMAND("Write some commands first");

    private final String reply;

    RequestReplies(String reply) { this.reply = reply; }

    public String getReply() { return reply; }
}
