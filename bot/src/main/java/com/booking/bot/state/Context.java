package com.booking.bot.state;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Context {
    private Integer messageId;
    private Long userId;
    private Stage stage;
    private Stage beforeStage;
    private String callbackData;
    private String type;
    private Integer page;
    public Context(Long userId) {
        this.userId = userId;
    }
}