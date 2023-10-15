package org.musxteam.core;

public enum RequestReplies {
    HELP("Hello, how can I help u?");

    private final String reply;
    RequestReplies(String reply) {
        this.reply = reply;
    }
    public String getReply() { return reply; }
}
