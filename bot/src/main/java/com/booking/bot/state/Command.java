package com.booking.bot.state;

public enum Command {
    START("/start"),
    STOP("/stop");
    private final String value;

    public String getValue() {
        return value;
    }

    Command(String value) {
        this.value = value;
    }
}
