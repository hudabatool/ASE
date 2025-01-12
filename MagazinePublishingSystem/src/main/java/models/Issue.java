/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * @author HudaB_21554056 */
import java.util.*;
import java.time.LocalDate;
import java.util.logging.Logger;

public class Issue {
	private static final Logger LOGGER = Logger.getLogger(Issue.class.getName());
	
    private int issueId;
    private String layout;
    private String status;
    private ArrayList<Story> stories;
    private ArrayList<Photograph> photographs;
    private ArrayList<Advertisement> advertisements;
    private ArrayList<Contributor> contributors; // New field for contributors
    private LocalDate publicationDate; // New field for publication date
    private boolean isApproved;
    private boolean isPublished;

    // Constructor
    public Issue(int issueId, String layout, String status, LocalDate publicationDate) {
        this.issueId = issueId;
        this.layout = layout;
        this.status = status;
        this.publicationDate = publicationDate;
        this.stories = new ArrayList<>();
        this.photographs = new ArrayList<>();
        this.advertisements = new ArrayList<>();
        this.contributors = new ArrayList<>();
        this.isApproved = false;
        this.isPublished = false;
        System.out.println("Issue created: ID " + issueId + ", Layout: " + layout + ", Status: " + status);
    }
    // Default constructor
    public Issue() {
    }


    // Methods to add stories, photographs, advertisements, and contributors
    public void addStory(Story story) {
        if (story == null) {
            LOGGER.severe("Story cannot be null.");
            return; 
        }
        // Associate the story with this issue
        story.setMagazineIssue(this); // This ensures that the story is linked to the current issue
        stories.add(story);
        System.out.println("Story added to Issue [ID: " + issueId + "]");
    }

    public void addPhotograph(Photograph photo) {
        if (photo == null) {
        	LOGGER.severe("Photograph cannot be null.");
        	 return; 
        }
        photographs.add(photo);
        System.out.println("Photograph added to Issue [ID: " + issueId + "]");
    }

    public void addAdvertisement(Advertisement ad) {
        if (ad == null) {
            LOGGER.severe("Advertisement cannot be null.");
            return; 
        }
        advertisements.add(ad);
        System.out.println("Advertisement added to Issue [ID: " + issueId + "]");
    }

    public void addContributor(Contributor contributor) {
        if (contributor == null) {
            LOGGER.severe("Contributor cannot be null.");
        }
        contributors.add(contributor);
        System.out.println("Contributor added to Issue [ID: " + issueId + "]");
    }

    // OCL Rule: Every magazine issue must include at least one story and one advertisement
    public boolean mustHaveContent() {
        if (stories.size() >= 1 && advertisements.size() >= 1) {
            System.out.println("Magazine issue has content (stories and advertisements).");
            return true;
        }
       LOGGER.warning("Magazine issue is missing stories or advertisements.");
        return false;
    }

    // OCL Rule: Editors must approve all magazine issues before publication
    public boolean editorApprovalRequired() {
        if (isApproved) {
            if (isPublished) {
                System.out.println("Editor approval granted, issue published.");
                return true;
            } else {
                LOGGER.info("Issue approved but not yet published.");
                return false;
            }
        }
        LOGGER.info("Editor approval required before publication.");
        return false;
    }

    // OCL Rule: Ensure contributors are paid within 30 days of publication
    public boolean arePaymentsOnTime() {
        for (Contributor contributor : contributors) {
            if (!contributor.isPaymentWithin30Days(publicationDate)) {
                return false;
            }
        }
        return true;
    }

    // Publish the issue
    public void publishIssue() {
        if (!isApproved) {
            LOGGER.warning("Cannot publish issue. Editor approval is required.");
            return;
        }
        if (!mustHaveContent()) {
            LOGGER.severe("Cannot publish issue. Missing necessary content (stories or advertisements).");
            return;
        }
        this.isPublished = true;
        System.out.println("Issue published successfully: ID " + issueId);
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
    public ArrayList<Advertisement> getAdvertisements() { return advertisements; }
    public ArrayList<Contributor> getContributors() { return contributors; }

    public LocalDate getPublicationDate() { return publicationDate; }
    public void setPublicationDate(LocalDate publicationDate) { this.publicationDate = publicationDate; }

    public boolean isApproved() { return isApproved; }
    public void setApproved(boolean isApproved) { this.isApproved = isApproved; }

    public boolean isPublished() { return isPublished; }
    public void setPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }
}

