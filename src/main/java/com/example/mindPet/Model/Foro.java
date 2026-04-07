package com.example.mindPet.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "foros")
public class Foro {
//Ya quedo/ /
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String author;
    private String content;
    private String image;

    @ElementCollection
    private List<String> likes;


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

}