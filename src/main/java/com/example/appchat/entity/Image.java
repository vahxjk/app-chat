package com.example.appchat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id",nullable = false)
    Integer id;

    @Column(name = "image_url",nullable = false)
    String url;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "message_id", columnDefinition = "message_id")
    Message message;
}
