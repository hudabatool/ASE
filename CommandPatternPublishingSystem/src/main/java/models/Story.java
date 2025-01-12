/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * @author HudaB_21554056 */
import java.util.Date;

public class Story {
    private String storyId;
    private String title;
    private String content;
    private String author;
    private Date publishDate;
    private String category;

    // Constructor
    public Story(String storyId, String title, String content, String author, Date publishDate, String category) {
        this.storyId = storyId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.publishDate = publishDate;
        this.category = category;
    }

    // Getters and Setters
    public String getStoryId() { return storyId; }
    public void setStoryId(String storyId) { this.storyId = storyId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Date getPublishDate() { return publishDate; }
    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}

