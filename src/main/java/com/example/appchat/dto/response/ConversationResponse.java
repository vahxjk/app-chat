package com.example.appchat.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationResponse {

    String title;

    String idChannel;

    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDateTime createdDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDateTime updatedDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDateTime deletedDate;

    Integer creatorId;

    List<MessageResponse> messages;
}
