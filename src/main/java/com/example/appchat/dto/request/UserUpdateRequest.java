package com.example.appchat.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String phone;

    String email;

    String password;

    String firstName;

    String lastName;

    String middleName;

    String preferences;
}