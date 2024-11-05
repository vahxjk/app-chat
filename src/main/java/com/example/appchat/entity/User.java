package com.example.appchat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Integer id;

    @Column(name = "phone",nullable = false,length = 16, unique = true)
    String phone;

    @Column(name = "email",nullable = false, unique = true)
    String email;

    @Column(name = "password",nullable = false)
    String password;

    @Column(name = "first_name",nullable = false)
    String firstName;

    @Column(name = "last_name",nullable = false)
    String lastName;

    @Column(name = "middle_name")
    String middleName;

    @Column(name = "is_active",nullable = false)
    boolean isActive = true;

    @Column(name = "is_reported",nullable = false)
    boolean isReported = false;

    @Column(name = "is_blocked",nullable = false)
    boolean isBlocked = false;

    @Column(name = "preferences", columnDefinition = "TEXT")
    String preferences;

    @Column(name = "created_at",updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDateTime createdDate;

    @Column(name = "updated_at")
    @UpdateTimestamp
    LocalDateTime updatedDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Contact> contacts;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Conversation> conversations;

    @OneToMany(mappedBy = "sender",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Message> messageList;
}
