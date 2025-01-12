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
import Marketing.MarketingDepartment;

import java.time.LocalDate;
import java.util.ArrayList;

public class MagazinePublishingSystem {
	public static void main(String[] args) {
        Editor editor = new Editor();
        AccountsDepartment accounts = new AccountsDepartment();
        Publisher publisher = new Publisher();
        MarketingDepartment marketingDepartment = new MarketingDepartment();

        // Creating contributors
        Contributor contributor1 = new Contributor(1, "Alice", "Author", 0.0);
        Contributor contributor2 = new Contributor(2, "Bob", "Photographer", 0.0);

        // Adding contributors to the system
        accounts.addContributor(contributor1);
        accounts.addContributor(contributor2);

        // OCL Rule: A story must belong to exactly one magazine issue
        System.out.println("\nTesting OCL Rule: A story must belong to exactly one magazine issue:");
        Story story1 = new Story("S1", "Tech Innovations", "Content on AI", "Alice", LocalDate.now(), "Technology");
        System.out.println("Positive Test Case - Associating story to one issue:");
        editor.submitStory(story1); // Valid story submission

        Story storyWithoutIssue = new Story("S2", "Unassigned Story", "Content on Tech", "Alice", LocalDate.now(), "Technology");
        System.out.println("Negative Test Case - Story not assigned to any issue:");
        if (!storyWithoutIssue.belongsToOneIssue()) {
            System.out.println("OCL Constraint Violated: Story not associated with an issue.");
        }

        // OCL Rule: A photograph must not remain unused for more than 6 months
        System.out.println("\nTesting OCL Rule: Archive unused photographs after 6 months:");
        Photograph oldPhoto = new Photograph("P2", "Old Photo", "Bob", "720p");
        oldPhoto.setSubmissionDate(LocalDate.now().minusMonths(7));
        System.out.println("Positive Test Case - Archiving unused photograph:");
        if (oldPhoto.shouldArchiveUnusedContent()) {
            System.out.println("OCL Constraint Satisfied: Photograph archived.");
        }

        Photograph recentPhoto = new Photograph("P3", "Recent Photo", "Bob", "1080p");
        recentPhoto.setSubmissionDate(LocalDate.now().minusMonths(3));
        System.out.println("Negative Test Case - Photograph not archived:");
        if (!recentPhoto.shouldArchiveUnusedContent()) {
            System.out.println("OCL Constraint Satisfied: Recent photograph is not archived.");
        }

        // OCL Rule: Contributors must be paid within 30 days of publication
        System.out.println("\nTesting OCL Rule: Contributor payments within 30 days:");
        Issue issue = new Issue(1, "Default Layout", "Draft", LocalDate.now());
        issue.addContributor(contributor1);
        issue.addContributor(contributor2);
        accounts.processContributorPayment(1, 100.0);
        accounts.processContributorPayment(2, 50.0);
        System.out.println("Positive Test Case - All contributors paid on time:");
        if (issue.arePaymentsOnTime()) {
            System.out.println("OCL Constraint Satisfied: All payments are within 30 days.");
        }

        Contributor unpaidContributor = new Contributor(3, "Charlie", "Guest Author", 0.0);
        accounts.addContributor(unpaidContributor);
        issue.addContributor(unpaidContributor);
        System.out.println("Negative Test Case - Contributor not paid:");
        if (!issue.arePaymentsOnTime()) {
            System.out.println("OCL Constraint Violated: Not all payments are within 30 days.");
        }

        // OCL Rule: Each story can have zero or more photographs associated
        System.out.println("\nTesting OCL Rule: Stories with valid photo association:");
        System.out.println("Positive Test Case - Valid association with photographs:");
        story1.validPhotoAssociation();

        System.out.println("Negative Test Case - No photographs associated:");
        Story noPhotoStory = new Story("S3", "No Photos", "Content on Tech", "Alice", LocalDate.now(), "Technology");
        noPhotoStory.validPhotoAssociation();
        
        // Pre and Post conditions check: Submit Advertisements
        System.out.println("\nTesting Advertisement Submission:");

        
        // Test Case 1: Advertisement with missing payment details
        Advertisement ad16 = new Advertisement(1, "Ad for Tech Gadgets", null); // Payment details are missing
        System.out.println("Test Case 1: Advertisement with missing payment details:");
        if (!marketingDepartment.submitAdvertisement(ad16)) {
            System.out.println("Test Passed: Advertisement with missing payment details cannot be submitted.");
        } else {
            System.out.println("Test Failed: Advertisement with missing payment details was submitted.");
        }

        // Test Case 2: Advertisement with valid payment details
        Advertisement ad26 = new Advertisement(2, "Ad for Auto Expo", "PaymentID-1234"); // Valid payment details
        System.out.println("\nTest Case 2: Advertisement with valid payment details:");
        if (marketingDepartment.submitAdvertisement(ad26)) {
            System.out.println("Test Passed: Advertisement with valid payment details was submitted successfully.");
        } else {
            System.out.println("Test Failed: Advertisement with valid payment details was not submitted.");
        }

        // Test Case 3: Advertisement successfully added to repository
        System.out.println("\nTest Case 3: Advertisement repository validation:");
        if (marketingDepartment.getAdvertisementsRepository().contains(ad26)) {
            System.out.println("Test Passed: Advertisement was successfully added to the repository.");
        } else {
            System.out.println("Test Failed: Advertisement was not added to the repository.");
        }

        System.out.println("");
        // OCL Rule: Advertisements must be paid before publication
        // Positive Test Case: Advertisement is paid
        Advertisement adPaid = new Advertisement(1, "Tech Corp", "Ad Content 1", "Paid via Credit", true);
        // Negative Test Case: Advertisement is not paid
        Advertisement adNotPaid = new Advertisement(2, "Auto Inc.", "Ad Content 2", "Pending", false);

        System.out.println("\nTesting OCL Rule: Advertisement payment required before publication:");
        
        // Positive Test Case: Advertisement is paid and ready for publication
        System.out.println("Positive Test Case - Advertisement is paid:");
        if (adPaid.isPaymentRequiredBeforePublication()) {
            System.out.println("Advertisement is paid and ready for publication.");
        }

        // Negative Test Case: Advertisement is not paid
        System.out.println("Negative Test Case - Advertisement is not paid:");
        if (!adNotPaid.isPaymentRequiredBeforePublication()) {
            System.out.println("OCL Constraint Violated: Advertisement payment is missing.");
        }
        
     // Testing OCL Rule: Advertisements must have unique identifiers
        System.out.println("\nTesting OCL Rule: Unique Advertisement IDs:");

        ArrayList<Advertisement> advertisements = new ArrayList<>();

        // Create valid advertisements with unique IDs
        Advertisement ad1 = new Advertisement(1, "Tech Corp", "Ad Content 1", 500.0);
        Advertisement ad2 = new Advertisement(2, "Auto Inc.", "Ad Content 2", 400.0);

        // Add valid advertisements
        advertisements.add(ad1);
        advertisements.add(ad2);

        // Positive Test Case - Unique IDs
        System.out.println("Positive Test Case - All IDs are unique:");
        if (Advertisement.hasUniqueAdvertisementIds(advertisements)) {
            System.out.println("OCL Constraint Satisfied: All advertisement IDs are unique.");
        } else {
            System.out.println("OCL Constraint Violated: Duplicate advertisement IDs found.");
        }

        // Negative Test Case - Duplicate IDs
        Advertisement duplicateAd = new Advertisement(1, "Duplicate Corp", "Duplicate Ad Content", 300.0);
        advertisements.add(duplicateAd);

        System.out.println("\nNegative Test Case - Duplicate IDs:");
        if (!Advertisement.hasUniqueAdvertisementIds(advertisements)) {
            System.out.println("OCL Constraint Violated: Duplicate advertisement IDs found.");
        } else {
            System.out.println("OCL Constraint Satisfied: All advertisement IDs are unique.");
        }
        
        // OCL Rule: Every magazine issue must include at least one story and one advertisement
        System.out.println("\nTesting OCL Rule: Every magazine issue must include at least one story and one advertisement");

        // Positive Test Case: Issue with at least one story and one advertisement
        Story story10 = new Story("S1", "Tech Innovations", "Content on AI", "Alice", LocalDate.now(), "Technology");
        Issue validIssue = new Issue(1, "Tech Layout", "Draft", LocalDate.now());
        validIssue.addStory(story10);
        validIssue.addAdvertisement(adPaid);
        System.out.println("\nPositive Test Case - Valid Issue with story and advertisement:");
        if (validIssue.mustHaveContent()) {
            System.out.println("OCL Constraint Satisfied: The issue contains both stories and advertisements.");
        }

        // Negative Test Case: Issue with no stories or advertisements
        Issue invalidIssue1 = new Issue(2, "Empty Layout", "Draft", LocalDate.now());
        System.out.println("\nNegative Test Case - Invalid Issue with no stories or advertisements:");
        if (!invalidIssue1.mustHaveContent()) {
            System.out.println("OCL Constraint Violated: The issue must include at least one story and one advertisement.");
        }
        // Negative Test Case: Issue with only stories, no advertisements
        Issue invalidIssue2 = new Issue(3, "No Ads Layout", "Draft", LocalDate.now());
        invalidIssue2.addStory(story10);
        
        System.out.println("\nNegative Test Case - Invalid Issue with only stories and no advertisements:");
        if (!invalidIssue2.mustHaveContent()) {
            System.out.println("OCL Constraint Violated: The issue must include at least one advertisement.");
        }
        // Negative Test Case: Issue with only advertisements, no stories
        Issue invalidIssue3 = new Issue(4, "No Story Layout", "Draft", LocalDate.now());
        invalidIssue3.addAdvertisement(adPaid);
        
        System.out.println("\nNegative Test Case - Invalid Issue with only advertisements and no stories:");
        if (!invalidIssue3.mustHaveContent()) {
            System.out.println("OCL Constraint Violated: The issue must include at least one story.");
        }
        
        // Testing OCL Rule: Editors must approve all magazine issues before publication
        System.out.println("\nTesting OCL Rule: Editors must approve all magazine issues before publication:");
        System.out.println("");
        Issue issue1 = new Issue(1, "Tech Monthly", "Draft", LocalDate.now());
        issue1.setPublished(false);
        issue1.setApproved(false);

        System.out.println("Test Case 1: Issue not approved and not published:");
        if (!issue1.editorApprovalRequired()) {
            System.out.println("OCL Constraint Violated: Editor approval required before publication.");
        } else {
            System.out.println("OCL Constraint Satisfied: Issue approved and published.");
        }
        
        System.out.println("");
        Issue issue2 = new Issue(2, "Auto Weekly", "Published", LocalDate.now());
        issue2.setPublished(true);
        issue2.setApproved(true);

        System.out.println("Test Case 2: Issue approved and published:");
        if (issue2.editorApprovalRequired()) {
            System.out.println("OCL Constraint Satisfied: Issue approved and published.");
        } else {
            System.out.println("OCL Constraint Violated: Editor approval required before publication.");
        }
        System.out.println("");
        Issue issue3 = new Issue(3, "Travel Digest", "Approved", LocalDate.now());
        issue3.setPublished(false);
        issue3.setApproved(true);

        System.out.println("Test Case 3: Issue approved but not published:");
        if (!issue3.editorApprovalRequired()) {
            System.out.println("OCL Constraint Violated: Issue approved but not yet published.");
        } else {
            System.out.println("OCL Constraint Satisfied: Issue approved and published.");
        }
        
        // Pre and Post conditions check: Compile Magazine Issue
        System.out.println("\nTesting Magazine Issue Compilation:");
        
        // Test Case 1: Issue with unfinalized stories
        System.out.println("\nTest Case 1: Issue with unfinalized stories:");
        Issue issueWithUnfinalizedStory = new Issue(2, "Custom Layout", "Draft", LocalDate.now());
        Story unfinalizedStory = new Story("S1", "Unfinalized Story", false); // Story not finalized
        Advertisement validAd1 = new Advertisement(1, "Ad for Electronics", "PaymentID-1234", true); // Valid ad

        issueWithUnfinalizedStory.addStory(unfinalizedStory);
        issueWithUnfinalizedStory.addAdvertisement(validAd1);
        editor.setCurrentIssue(issueWithUnfinalizedStory);

        if (editor.compileMagazineIssue() == null) {
            System.out.println("Test Passed: Issue compilation failed due to unfinalized stories.");
        } else {
            System.out.println("Test Failed: Issue compiled despite unfinalized stories.");
        }

        // Test Case 2: Valid Issue
        System.out.println("\nTest Case 2: Valid issue with finalized stories and advertisements:");
        Issue validIssue3 = new Issue(3, "Premium Layout", "Draft", LocalDate.now());
        Story finalizedStory = new Story("S2", "Finalized Story", true); // Story finalized
        Advertisement validAd2 = new Advertisement(2, "Ad for Cars", "PaymentID-5678", true); // Ad finalized

        validIssue3.addStory(finalizedStory);
        validIssue3.addAdvertisement(validAd2);
        editor.setCurrentIssue(validIssue3);

        Issue compiledIssue = editor.compileMagazineIssue();
        if (compiledIssue != null && "Finalized".equals(compiledIssue.getStatus())) {
            System.out.println("Test Passed: Issue compiled successfully and status set to Finalized.");
        } else {
            System.out.println("Test Failed: Issue compilation failed despite meeting all conditions.");
        }

        // Publishing the magazine issue
        System.out.println("\nCompiling and Publishing the Magazine Issue:");
        editor.submitStory(story1);
        editor.submitPhotograph(recentPhoto);
        issue.addStory(story1);
        issue.addPhotograph(recentPhoto);
        publisher.publishIssue(issue);

        // Rejection scenario - Issue missing content
        System.out.println("\nNegative Test Case - Publishing issue with missing content:");
        Issue emptyIssue = new Issue(2, "No Content Layout", "Draft", LocalDate.now());
        publisher.publishIssue(emptyIssue); // Should fail due to missing content

        // Rejection scenario - Trying to publish a null issue
        System.out.println("\nNegative Test Case - Publishing a null issue:");
        publisher.publishIssue(null);

        System.out.println("\nPublishing a valid issue:");
        publisher.publishIssue(issue); // Valid issue
        
        //  Pre and Post conditions check: Process Payments
        System.out.println("\nTesting Process Payments:");

	     // Create contributors with non-zero balances
	     Contributor contributor17 = new Contributor(1, "John Doe", "writer", 100.0);  // Non-zero balance
	     Contributor contributor27 = new Contributor(2, "Jane Smith", "photographer", 150.0);  // Non-zero balance
	     Contributor contributor37 = new Contributor(3, "Bob Brown", "writer", 0.0); // Invalid balance
	
	     // Create an issue with contributors
	     Issue issue7 = new Issue(1, "Issue Layout", "Draft", LocalDate.now());
	     issue7.addContributor(contributor17);
	     issue7.addContributor(contributor27);
	     issue7.addContributor(contributor37);  // Contributor with invalid balance
	
	     // Test Case 1: Issue with contributors having valid and invalid balances
	     System.out.println("\nTest Case 1: Issue with contributors having valid and invalid balances:");
	     accounts.processPayments(issue7);
	
	     // Test Case 2: Issue with missing publication date
	     System.out.println("\nTest Case 2: Issue with missing publication date:");
	     Issue issueWithNoPublicationDate = new Issue(2, "Another Layout", "Draft", null);
	     issueWithNoPublicationDate.addContributor(contributor17);
	     issueWithNoPublicationDate.addContributor(contributor27);
	     accounts.processPayments(issueWithNoPublicationDate);
	
	     // Test Case 3: Issue with valid contributors and proper publication date
	     System.out.println("\nTest Case 3: Issue with valid contributors and publication date:");
	     Issue validIssue7 = new Issue(3, "Final Layout", "Draft", LocalDate.now().minusDays(10)); // 10 days ago
	     validIssue7.addContributor(contributor17);
	     validIssue7.addContributor(contributor27);
	
	     // Process payments within 30 days of publication
	     accounts.processPayments(validIssue7);
	
	     // Test Case 4: Issue with valid contributors and 30 days boundary condition
	     System.out.println("\nTest Case 4: Issue with valid contributors and 30 days boundary condition:");
	     Issue boundaryIssue = new Issue(4, "Boundary Layout", "Draft", LocalDate.now().minusDays(30)); // Exactly 30 days ago
	     contributor17.setPaymentStatus("Not Processed");
	     contributor27.setPaymentStatus("Not Processed");
	     boundaryIssue.addContributor(contributor17);
	     boundaryIssue.addContributor(contributor27);
	
	     // Process payments exactly 30 days after publication
	     accounts.processPayments(boundaryIssue);
	
	     // Test Case 5: Issue with contributors and payments processed after 30 days
	     System.out.println("\nTest Case 5: Issue with contributors and payments processed after 30 days:");
	     Issue overdueIssue = new Issue(5, "Overdue Layout", "Draft", LocalDate.now().minusDays(35)); // 35 days ago
	     contributor17.setPaymentStatus("Not Processed");
	     contributor27.setPaymentStatus("Not Processed");
	     overdueIssue.addContributor(contributor17);
	     overdueIssue.addContributor(contributor27);
	
	     // Process payments 35 days after publication (should trigger warning)
	     accounts.processPayments(overdueIssue);
		}
	}
