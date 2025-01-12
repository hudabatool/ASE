/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * @author HudaB_21554056 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Story {
	private static final Logger LOGGER = Logger.getLogger(Story.class.getName());
    private String storyId;
    private String title;
    private String content;
    private String author;
    private LocalDate publishDate;
    private String category;
    private boolean isPublished;
    private LocalDate submissionDate;
    private ArrayList<Photograph> photos;
    private Issue magazineIssue;
    private boolean isFinalized;

    // Constructor
    public Story(String storyId, String title, String content, String author, LocalDate publishDate, String category) {
        this.storyId = storyId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.publishDate = publishDate;
        this.category = category;
        this.photos = new ArrayList<>();
        this.magazineIssue = null;
    }
    
    public Story(String storyId, String title, boolean isFinalized) {
        this.storyId = storyId;
        this.title = title;
        this.isFinalized = isFinalized;
    }
    
   // OCL Rule: Unused stories are archived after 6 months
    public boolean shouldArchiveUnusedContent() {
        if (!isPublished && submissionDate.isBefore(LocalDate.now().minusMonths(6))) {
        	LOGGER.info("Story archived due to being unused for over 6 months.");
            return true;
        }
        System.out.println("Story is not archived, as it's either published or not older than 6 months.");
        return false;
    }

    // Ensure the story belongs to exactly one magazine issue
    public boolean belongsToOneIssue() {
        if (magazineIssue != null) {
            System.out.println("Story belongs to one issue.");
            return true;
        }
        LOGGER.info("Story is not associated with any issue.");
        return false;
    }
    
    // OCL Rule: Each story can have zero or more photographs associated with it.
    public boolean validPhotoAssociation() {
        // If photos is null, we consider it as zero photo association, which is valid
        if (photos == null) {
            System.out.println("Story has no photographs associated.");
            return true;
        }

        // If photos is not null, it should be a valid list with zero or more photos
        System.out.println("Story has valid photo association (zero or more photos).");
        return true;
    }
    
    // Getters and Setters
    public void setMagazineIssue(Issue issue) {
        this.magazineIssue = issue;
    }
    
    public String getStoryId() { return storyId; }
    public void setStoryId(String storyId) { this.storyId = storyId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public LocalDate getPublishDate() { return publishDate; }
    public void setPublishDate(LocalDate publishDate) { this.publishDate = publishDate; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public boolean isFinalized() { return isFinalized; }

    public void setFinalized(boolean finalized) { isFinalized = finalized; }
}

