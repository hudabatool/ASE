/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

/**
 *
 * @author hudab
 */
import editor.Editor;
import models.*;
import publisher.Publisher;
import accounts.AccountsDepartment;

import java.util.Date;

public class MagazinePublishingSystem {
    public static void main(String[] args) {
        // Initialize editor and accounts department
        Editor editor = new Editor();
        AccountsDepartment accounts = new AccountsDepartment();
        Publisher publisher = new Publisher();

        // Add contributors
        Contributor contributor1 = new Contributor(1, "Alice", "Author", 0.0);
        Contributor contributor2 = new Contributor(2, "Bob", "Photographer", 0.0);
        accounts.addContributor(contributor1);
        accounts.addContributor(contributor2);

        // Create and submit a story
        Story story = new Story("S1", "Tech Innovations", "Content on AI", "Alice", new Date(), "Technology");
        editor.submitStory(story);

        // Create and submit a photograph
        Photograph photo = new Photograph("P1", "AI Research Lab", "Bob", "1080p");
        editor.submitPhotograph(photo);

        // Match photograph to story
        editor.matchPhotographToStory(1, 1);

        // Process payment for contributors
        accounts.processContributorPayment(1, 100.0);
        accounts.processContributorPayment(2, 50.0);

        // Submit and edit advertisements
        Advertisement ad1 = new Advertisement(1, "Tech Corp", "Ad Content 1", 500.0);
        publisher.submitAdvertisement(ad1);
        Advertisement updatedAd = new Advertisement(1, "Tech Corp", "Updated Ad Content", 600.0);
        publisher.editAdvertisement(1, updatedAd);

        // Compile and publish magazine issue
        Issue issue = editor.compileMagazineIssue();
        publisher.publishIssue(issue);
    }
}
