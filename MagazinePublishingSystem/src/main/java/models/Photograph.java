/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author hudab
 */
public class Photograph {
    private String photoId;
    private String caption;
    private String photographer;
    private String resolution;

    // Constructor
    public Photograph(String photoId, String caption, String photographer, String resolution) {
        this.photoId = photoId;
        this.caption = caption;
        this.photographer = photographer;
        this.resolution = resolution;
    }

    // Getters and Setters
    public String getPhotoId() { return photoId; }
    public void setPhotoId(String photoId) { this.photoId = photoId; }

    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }

    public String getPhotographer() { return photographer; }
    public void setPhotographer(String photographer) { this.photographer = photographer; }

    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }
}

