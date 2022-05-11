package com.booking.bot.state;

public enum Command {
    START("/start");
    private final String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
