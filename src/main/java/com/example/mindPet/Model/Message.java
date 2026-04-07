package com.example.mindPet.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String sender; // "USER" o "AI"
    private LocalDateTime timestamp;

    public Message() { this.timestamp = LocalDateTime.now(); }

    public Message(String content, String sender) {
        this.content = content;
        this.sender = sender;
        this.timestamp = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
}
