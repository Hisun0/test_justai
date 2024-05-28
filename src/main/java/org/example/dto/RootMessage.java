package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RootMessage {
    private long group_id;
    private String type;
    private String event_id;
    private String v;
    private NewMessageObject object;
}
