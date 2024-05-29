package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Message {
    private Timestamp date;
    private long from_id;
    private long id;
    private long out;
    private long version;
    private Object[] attachments;
    private long conversation_message_id;
    private Message[] fwd_messages;
    private Boolean important;
    private Boolean is_hidden;
    private long peer_id;
    private long random_id;
    private String text;
    private Boolean is_mentioned_user;
}
