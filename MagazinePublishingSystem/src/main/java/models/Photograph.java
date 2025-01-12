/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * @author HudaB_21554056 */
import java.time.LocalDate;
import java.util.logging.Logger;

public class Photograph {
	private static final Logger LOGGER = Logger.getLogger(Photograph.class.getName());
	
    private String photoId;
    private String caption;
    private String photographer;
    private String resolution;
    private boolean isPublished;
    private LocalDate submissionDate;

    // Constructor
    public Photograph(String photoId, String caption, String photographer, String resolution) {
        this.photoId = photoId;
        this.caption = caption;
        this.photographer = photographer;
        this.resolution = resolution;
        this.submissionDate = LocalDate.now();
    }
    
    // OCL Rule: Unused photographs are archived after 6 months
    public boolean shouldArchiveUnusedContent() {
        if (!isPublished && submissionDate.isBefore(LocalDate.now().minusMonths(6))) {
        	LOGGER.info("Photograph archived due to being unused for over 6 months.");
            return true;
        }
        System.out.println("Photograph is not archived, as it's either published or not older than 6 months.");
        return false;
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
    
    public LocalDate getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(LocalDate submissionDate) { this.submissionDate = submissionDate; }

}

