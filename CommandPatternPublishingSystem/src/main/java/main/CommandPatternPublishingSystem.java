/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

/**
 * @author HudaB_21554056 */
import editor.Editor;
import models.*;
import publisher.Publisher;
import accounts.AccountsDepartment;
import commands.*;
import invoker.Invoker;

import java.util.Date;

public class CommandPatternPublishingSystem {
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

        // Using Command Pattern for additional actions
        Invoker invoker = new Invoker();

        // Execute Commands
        // Approve Story
        invoker.setCommand(new ApproveStoryCommand(editor));
        invoker.invoke();

        // Reject Story
        invoker.setCommand(new RejectStoryCommand(editor, "Story123"));
        invoker.invoke();

        // Finalize Magazine Issue
        invoker.setCommand(new FinalizeMagazineCommand(publisher, "Issue2025-Jan"));
        invoker.invoke();

        // Process Payments
        invoker.setCommand(new PaymentProcessingCommand(accounts));
        invoker.invoke();

        System.out.println("Magazine Publishing Workflow Completed.");
    }
}

