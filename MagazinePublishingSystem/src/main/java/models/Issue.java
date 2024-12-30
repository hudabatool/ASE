/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author hudab
 */
import java.util.ArrayList;

public class Issue {
    private int issueId;
    private String layout;
    private String status;
    private ArrayList<Story> stories;
    private ArrayList<Photograph> photographs;

    public Issue(int issueId, String layout, String status) {
        this.issueId = issueId;
        this.layout = layout;
        this.status = status;
        this.stories = new ArrayList<>();
        this.photographs = new ArrayList<>();
    }

    // Methods to add stories and photographs
    public void addStory(Story story) {
        stories.add(story);
    }

    public void addPhotograph(Photograph photo) {
        photographs.add(photo);
    }

    // Getters and Setters
    public int getIssueId() { return issueId; }
    public void setIssueId(int issueId) { this.issueId = issueId; }

    public String getLayout() { return layout; }
    public void setLayout(String layout) { this.layout = layout; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public ArrayList<Story> getStories() { return stories; }
    public ArrayList<Photograph> getPhotographs() { return photographs; }
}

