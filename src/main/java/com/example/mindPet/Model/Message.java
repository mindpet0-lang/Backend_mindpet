package com.example.mindPet.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId; // Relación con el usuario para chats individuales

    @Column(columnDefinition = "TEXT")
    private String content;

    private String sender; // "USER" o "AI"
    private LocalDateTime timestamp;

    public Message() {
        this.timestamp = LocalDateTime.now();
    }

    public Message(String content, String sender, Long userId) {
        this.content = content;
        this.sender = sender;
        this.userId = userId;
        this.timestamp = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public LocalDateTime getTimestamp() { return timestamp; }
}