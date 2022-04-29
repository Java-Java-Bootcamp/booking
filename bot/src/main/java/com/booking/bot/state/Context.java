package com.booking.bot.state;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Context {
    private Integer messageId;
    private Long userId;
    private Command command;
    private Stage stage;
    public Context(Long userId,Integer messageId) {
        this.userId = userId;
        this.messageId = messageId;
    }
}