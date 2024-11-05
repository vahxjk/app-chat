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
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "conversations")
public class Conversation {
    @Id
    @Column(name = "conversation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "title",length = 100,nullable = false)
    String title;

    @Column(name = "channel_id",nullable = false, length = 45)
    String idChannel;

    @Column(name = "created_at", updatable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @CreationTimestamp
    LocalDateTime createdDate;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @UpdateTimestamp
    LocalDateTime updatedDate;

    @Column(name = "deleted_at")
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDateTime deletedDate;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "user_id")
    User creator;

    @OneToMany(mappedBy = "conversation",cascade = CascadeType.ALL, orphanRemoval = true)
    List<Message> messages;

}
