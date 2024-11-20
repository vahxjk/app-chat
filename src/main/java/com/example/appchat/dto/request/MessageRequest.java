package com.example.appchat.dto.request;

import com.example.appchat.entity.Message;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageRequest {

    Message.MessageType messageType;

    String message;

}
