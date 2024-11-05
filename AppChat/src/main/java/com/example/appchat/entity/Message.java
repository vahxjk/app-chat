package com.example.appchat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id",nullable = false)
    Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type", nullable = false)
    private MessageType messageType;

    @Column(name = "message",columnDefinition = "TEXT", nullable = false)
    String message;

    @Column(name = "created_at", updatable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @CreationTimestamp
    LocalDateTime createdDate;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @UpdateTimestamp
    LocalDateTime updatedDate;

    public enum MessageType {
        TEXT,
        IMAGE,
        VIDEO,
        AUDIO
    }

    @ManyToOne
    @JoinColumn(name = "conversation_id", columnDefinition = "conversation_id")
    Conversation conversation;

    @ManyToOne
    @JoinColumn(name = "sender_id", columnDefinition = "user_id")
    User sender;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    List<File> files;

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL,orphanRemoval = true)
    List<Image> images;
}
