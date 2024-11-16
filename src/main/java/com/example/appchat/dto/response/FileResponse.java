package com.example.appchat.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileResponse {
    String url;

    String type;

    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDateTime createDate;

    MessageResponse message;
}
