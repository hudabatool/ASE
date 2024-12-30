/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package publisher;

/**
 *
 * @author hudab
 */
import models.Advertisement;
import models.Issue;
import system.IAdvertisementManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Publisher implements IAdvertisementManager {
    private HashMap<Integer, Advertisement> advertisements;
    private ArrayList<Issue> publishedIssues;

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
}
