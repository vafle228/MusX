package org.musxteam.core;

public enum RequestReplies {
    HELP("Hello, how can I help u?"),
    SEARCH_START("Введите поисковой запрос"),
    DOWNLOAD_START("Введите id трека"),
    EMPTY_COMMAND("Write some commands first");

    private final String reply;

    RequestReplies(String reply) { this.reply = reply; }

    public String getReply() { return reply; }
}
