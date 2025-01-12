/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package publisher;

/**
 * @author HudaB_21554056 */
import models.Advertisement;
import models.Issue;
import system.IAdvertisementManager;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Publisher implements IAdvertisementManager {
    private static final Logger logger = Logger.getLogger(Publisher.class.getName());
    private HashMap<Integer, Advertisement> advertisements;
    private ArrayList<Issue> publishedIssues;
    private List<String> finalizedIssues = new ArrayList<>();

    public Publisher() {
        this.advertisements = new HashMap<>();
        this.publishedIssues = new ArrayList<>();
    }

    @Override
    public void submitAdvertisement(Advertisement ad) {
        advertisements.put(ad.getAdId(), ad);
        System.out.println("Advertisement submitted: " + ad.getAdvertiserName());
    }

    @Override
    public Advertisement getAdvertisementDetails(int adID) {
        if (advertisements.containsKey(adID)) {
            return advertisements.get(adID);
        } else {
            System.out.println("Advertisement not found for ID: " + adID);
            return null;
        }
    }

    @Override
    public void editAdvertisement(int adID, Advertisement newDetails) {
        if (advertisements.containsKey(adID)) {
            advertisements.put(adID, newDetails);
            System.out.println("Advertisement edited for ID: " + adID);
        } else {
            System.out.println("Advertisement not found for ID: " + adID);
        }
    }

    public void publishIssue(Issue issue) {
        publishedIssues.add(issue);
        System.out.println("Issue published: Issue ID " + issue.getIssueId());
    }
    
    public void finalizeIssue(String issue) {
        logger.info("Finalizing issue: " + issue);
        finalizedIssues.add(issue);
        System.out.println("Issue finalized: " + issue);
    }

    public void undoFinalizeIssue(String issue) {
        if (finalizedIssues.contains(issue)) {
            logger.info("Undoing finalization of issue: " + issue);
            finalizedIssues.remove(issue);
            System.out.println("Finalization undone for issue: " + issue);
        } else {
            logger.warning("Attempt to undo finalization of non-existent issue: " + issue);
        }
    }
}
