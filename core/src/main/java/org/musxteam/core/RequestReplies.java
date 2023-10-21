package org.musxteam.core;

public enum RequestReplies {
    ECHO_START("Write a message"),
    HELP("Hello, how can I help u?"),
    EMPTY_COMMAND("Write some commands first");

    private final String reply;
    RequestReplies(String reply) {
        this.reply = reply;
    }
    public String getReply() { return reply; }
}
