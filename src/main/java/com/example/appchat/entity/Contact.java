package com.example.appchat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "contacts")
public class Contact {

    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "username",nullable = false)
    String username;

    @Column(name = "phone",nullable = false)
    String phone;

    @Column(name = "email",nullable = false)
    String email;

    @Column(name = "created_at",updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    User user;
}
