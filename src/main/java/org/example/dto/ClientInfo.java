package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientInfo {
    private String[] button_actions;
    private boolean keyboard;
    private boolean inline_keyboard;
    private boolean carousel;
    private long lang_id;
}
