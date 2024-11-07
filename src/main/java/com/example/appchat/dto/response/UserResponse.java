package com.example.appchat.dto.response;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;

    String phone;

    String email;

    String password;

    String firstName;

    String lastName;

    String middleName;

    boolean isActive = true;

    boolean isReported = false;

    boolean isBlocked = false;

    String preferences;
}
