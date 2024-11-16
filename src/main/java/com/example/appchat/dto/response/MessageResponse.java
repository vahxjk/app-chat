package com.example.appchat.dto.response;

import com.example.appchat.entity.Message;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponse {
    Integer id;

    Message.MessageType messageType;

    String message;

    LocalDateTime createdDate;

    LocalDateTime updatedDate;

    Integer conversationId;

    Integer senderId;
}
