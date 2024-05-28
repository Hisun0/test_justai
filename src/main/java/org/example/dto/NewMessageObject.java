package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewMessageObject {
    private Message message;
    private ClientInfo client_info;
}
