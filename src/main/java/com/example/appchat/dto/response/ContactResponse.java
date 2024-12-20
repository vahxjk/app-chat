package com.example.appchat.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactResponse {
    String username;

    String phone;

    String email;

    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDateTime createdDate;;

}
