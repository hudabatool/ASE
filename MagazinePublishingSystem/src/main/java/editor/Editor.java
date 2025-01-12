/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package editor;

/**
 * @author HudaB_21554056 */
import models.*;
import system.IContentManager;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Editor implements IContentManager {
    private static final Logger LOGGER = Logger.getLogger(Editor.class.getName());
    
    private Issue currentIssue;
	
    @Override
    public void submitStory(Story story) {
        if (story == null) {
            LOGGER.log(Level.WARNING, "Cannot submit a null story.");
            return;
        }
        if (story.getTitle() == null || story.getTitle().isEmpty()) {
            LOGGER.log(Level.WARNING, "Story title cannot be null or empty.");
            return;
        }
        System.out.println("Story submitted: " + story.getTitle());
    }

    @Override
    public void submitPhotograph(Photograph photograph) {
        if (photograph == null) {
            LOGGER.log(Level.WARNING, "Cannot submit a null photograph.");
            return;
        }
        if (photograph.getCaption() == null || photograph.getCaption().isEmpty()) {
            LOGGER.log(Level.WARNING, "Photograph caption cannot be null or empty.");
            return;
        }
        System.out.println("Photograph submitted: " + photograph.getCaption());
    }

    @Override
    public void matchPhotographToStory(int photoID, int storyID) {
        if (photoID <= 0 || storyID <= 0) {
            LOGGER.log(Level.WARNING, "Invalid IDs for matching: PhotoID={0}, StoryID={1}", new Object[]{photoID, storyID});
            return;
        }
        System.out.println("Photograph " + photoID + " matched to Story " + storyID);
    }

    @Override
    public Issue compileMagazineIssue() {
    	if (currentIssue == null) {
            LOGGER.severe("No current issue set for the editor.");
            return null;
        }

        Issue issue = this.currentIssue; // Use the existing issue
        LOGGER.info("Number of stories: " + issue.getStories().size());
        LOGGER.info("Number of advertisements: " + issue.getAdvertisements().size());

        // OCL Rule: Every magazine issue must include at least one story and one advertisement.
        if (issue.getStories().size() < 1 || issue.getAdvertisements().size() < 1) {
            LOGGER.log(Level.WARNING, "Cannot compile magazine issue without at least one story and one advertisement.");
            return null;
        }

        // Precondition: All content (stories, ads, and photographs) must be finalized.
        if (issue.getStories().stream().anyMatch(story -> !story.isFinalized())) {
            LOGGER.log(Level.WARNING, "One or more stories are not finalized.");
            return null;
        }

        if (issue.getAdvertisements().stream().anyMatch(ad -> !ad.isFinalized())) {
            LOGGER.log(Level.WARNING, "One or more advertisements are not finalized.");
            return null;
        }

        // Postcondition: Issue is stored in the system.
        issue.setStatus("Finalized");
        System.out.println("Magazine issue compiled: Issue ID " + issue.getIssueId());
        return issue;
    }

    // OCL Rule: Editors must approve all magazine issues before publication.
    public void approveIssue(Issue issue) {
        if (issue == null) {
            LOGGER.log(Level.WARNING, "Cannot approve a null issue.");
            return;
        }
        if (issue.getStatus().equals("Finalized")) {
            issue.setApproved(true);
            System.out.println("Magazine issue approved: Issue ID " + issue.getIssueId());
        } else {
            LOGGER.log(Level.WARNING, "Cannot approve an issue that is not finalized.");
        }
    }

    public void setCurrentIssue(Issue issue) {
        this.currentIssue = issue;
    }
}

