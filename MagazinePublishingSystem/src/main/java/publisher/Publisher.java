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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Publisher implements IAdvertisementManager {
	private static final Logger LOGGER = Logger.getLogger(Publisher.class.getName());
    private HashMap<Integer, Advertisement> advertisements = new HashMap<>();
    private ArrayList<Issue> publishedIssues = new ArrayList<>();

    @Override
    public void submitAdvertisement(Advertisement ad) {
        if (ad == null) {
            LOGGER.log(Level.WARNING, "Cannot submit a null advertisement.");
            return;
        }
        advertisements.put(ad.getAdId(), ad);
        System.out.println("Advertisement submitted: " + ad.getAdvertiserName());
    }

    @Override
    public Advertisement getAdvertisementDetails(int adID) {
        Advertisement ad = advertisements.get(adID);
        if (ad == null) {
            LOGGER.log(Level.WARNING, "Advertisement not found for ID: {0}", adID);
        }
        return ad;
    }

    @Override
    public void editAdvertisement(int adID, Advertisement newDetails) {
        if (!advertisements.containsKey(adID) || newDetails == null) {
            LOGGER.log(Level.WARNING, "Invalid advertisement edit request: ID={0}", adID);
            return;
        }
        advertisements.put(adID, newDetails);
        System.out.println("Advertisement edited for ID: " + adID);
    }

    public void publishIssue(Issue issue) {
        if (issue == null) {
            LOGGER.log(Level.WARNING, "Cannot publish a null issue.");
            return;
        }
        publishedIssues.add(issue);
        System.out.println("Issue published: Issue ID " + issue.getIssueId());
    }
}
