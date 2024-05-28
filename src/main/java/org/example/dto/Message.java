package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private boolean important;
    @JsonProperty("is_hidden") // пришлось добавить аннотацию, так как почему-то вылетала UnrecognizedPropertyException
    private boolean is_hidden;
    private long peer_id;
    private long random_id;
    private String text;
    @JsonProperty("is_mentioned_user") // пришлось добавить аннотацию, так как почему-то вылетала UnrecognizedPropertyException
    private boolean is_mentioned_user;
}
