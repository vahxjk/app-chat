package com.example.appchat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id",nullable = false)
    Integer id;

    @Column(name = "file_url", nullable = false)
    String url;

    @Column(name = "file_type",nullable = false, length = 50)
    String type;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "created_at", updatable = false)
    LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "message_id")
    Message message;
}
