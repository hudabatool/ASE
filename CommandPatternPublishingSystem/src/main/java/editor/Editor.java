/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package editor;

/**
 * @author HudaB_21554056 */
import models.Issue;
import models.Story;
import models.Photograph;
import system.IContentManager;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

public class Editor implements IContentManager {
    private static final Logger logger = Logger.getLogger(Editor.class.getName());
    private List<String> rejectedStories = new ArrayList<>();

    // Helper method for logging invalid submissions
    private void logInvalidSubmission(String type, String field) {
        String errorMessage = type + " submission failed: " + field + " is null or empty.";
        logger.severe(errorMessage);
        throw new IllegalArgumentException(errorMessage);
    }

    @Override
    public void submitStory(Story story) {
        if (story == null || story.getTitle() == null || story.getTitle().isEmpty()) {
            logInvalidSubmission("Story", "Title");
        }
        logger.info("Submitting story: " + story.getTitle());
        System.out.println("Story submitted: " + story.getTitle());
    }

    @Override
    public void submitPhotograph(Photograph photograph) {
        if (photograph == null || photograph.getCaption() == null || photograph.getCaption().isEmpty()) {
            logInvalidSubmission("Photograph", "Caption");
        }
        logger.info("Submitting photograph: " + photograph.getCaption());
        System.out.println("Photograph submitted: " + photograph.getCaption());
    }

    @Override
    public void matchPhotographToStory(int photoID, int storyID) {
        if (photoID <= 0 || storyID <= 0) {
            String errorMessage = "Invalid IDs for matching: photoID=" + photoID + ", storyID=" + storyID;
            logger.severe(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        logger.info("Matching photograph " + photoID + " to story " + storyID);
        System.out.println("Photograph " + photoID + " matched to Story " + storyID);
    }

    @Override
    public Issue compileMagazineIssue() {
        logger.info("Starting compilation of magazine issue...");
        try {
            Issue issue = new Issue(1, "Default Layout", "Draft");
            logger.info("Magazine issue compiled successfully: Issue ID " + issue.getIssueId());
            System.out.println("Magazine issue compiled: Issue ID " + issue.getIssueId());
            return issue;
        } catch (Exception e) {
            String errorMessage = "Error during magazine issue compilation: " + e.getMessage();
            logger.severe(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public void approveStory() {
        logger.info("Approving story...");
        try {            
        	logger.info("Story approved successfully.");
            System.out.println("Story approved successfully.");
        } catch (Exception e) {
            String errorMessage = "Error during story approval: " + e.getMessage();
            logger.severe(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }
    
    public void rejectStory(String story) {
        rejectedStories.add(story);
        System.out.println("Story \"" + story + "\" has been rejected.");
    }

    public void undoRejectStory(String story) {
        if (rejectedStories.contains(story)) {
            rejectedStories.remove(story);
            System.out.println("Rejection of story \"" + story + "\" has been undone.");
        } else {
            System.out.println("No rejection found for story \"" + story + "\" to undo.");
        }
    }
}

