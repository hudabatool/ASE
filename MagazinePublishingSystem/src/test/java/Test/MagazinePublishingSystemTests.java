/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;
/**
 * @author HudaB_21554056 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import models.*;
import editor.Editor;
import publisher.Publisher;
import accounts.AccountsDepartment;
import Marketing.MarketingDepartment;

import java.time.LocalDate;
import java.util.ArrayList;

public class MagazinePublishingSystemTests {
	
    @Test
    public void testStoryBelongsToExactlyOneIssue() {
        //Create a Magazine Issue
        Issue issue = new Issue(1, "Tech Monthly", "January 2025", LocalDate.now());

        // Create a Story and associate it with the issue
        Story story = new Story("S1", "Tech Innovations", "Content on AI", "Alice", LocalDate.now(), "Technology");
        issue.addStory(story); // This will associate the story with the issue

        // Create an unassigned story
        Story unassignedStory = new Story("S2", "Unassigned Story", "Content on Tech", "Alice", LocalDate.now(), "Technology");

        // Assertions to validate OCL constraint
        assertTrue(story.belongsToOneIssue(), "Story should belong to exactly one issue.");  // Expect this to pass
        assertFalse(unassignedStory.belongsToOneIssue(), "Story not assigned to any issue should violate the OCL rule.");  // Expect this to fail
    }

    @Test
    public void testPhotographArchiveRule() {
        Photograph oldPhoto = new Photograph("P2", "Old Photo", "Bob", "720p");
        oldPhoto.setSubmissionDate(LocalDate.now().minusMonths(7));

        Photograph recentPhoto = new Photograph("P3", "Recent Photo", "Bob", "1080p");
        recentPhoto.setSubmissionDate(LocalDate.now().minusMonths(3));

        assertTrue(oldPhoto.shouldArchiveUnusedContent(), "Photograph unused for over 6 months should be archived.");
        assertFalse(recentPhoto.shouldArchiveUnusedContent(), "Photograph unused for less than 6 months should not be archived.");
    }

    @Test
       public void testContributorPaymentWithin30Days() {
        AccountsDepartment accounts = new AccountsDepartment();
        Contributor contributor1 = new Contributor(1, "Alice", "Author", 200.0);
        Contributor contributor2 = new Contributor(2, "Bob", "Photographer", 100.0);

        Issue issue = new Issue(1, "Default Layout", "Published", LocalDate.now());
        issue.addContributor(contributor1);
        issue.addContributor(contributor2);

        accounts.processContributorPayment(1, 200.0);
        accounts.processContributorPayment(2, 100.0);

        contributor1.setLastPaymentDate(issue.getPublicationDate().plusDays(15));
        contributor2.setLastPaymentDate(issue.getPublicationDate().plusDays(5));

        assertTrue(issue.arePaymentsOnTime(), "Payments within 30 days should satisfy the OCL rule.");

        Contributor lateContributor = new Contributor(3, "Charlie", "Guest Author", 300.0);
        issue.addContributor(lateContributor);
        lateContributor.setLastPaymentDate(issue.getPublicationDate().plusDays(35));

        assertFalse(issue.arePaymentsOnTime(), "Payments after 30 days should violate the OCL rule.");
    }

    @Test
    public void testAdvertisementSubmission() {
        MarketingDepartment marketing = new MarketingDepartment();

        Advertisement adWithPayment = new Advertisement(1, "Ad for Tech Gadgets", "PaymentID-1234");
        Advertisement adWithoutPayment = new Advertisement(2, "Ad for Auto Expo", null);

        assertTrue(marketing.submitAdvertisement(adWithPayment), "Advertisement with valid payment should be submitted.");
        assertFalse(marketing.submitAdvertisement(adWithoutPayment), "Advertisement with missing payment details should not be submitted.");
    }

    @Test
    public void testUniqueAdvertisementIds() {
        ArrayList<Advertisement> advertisements = new ArrayList<>();

        Advertisement ad1 = new Advertisement(1, "Tech Corp", "Ad Content 1", 500.0);
        Advertisement ad2 = new Advertisement(2, "Auto Inc.", "Ad Content 2", 400.0);
        advertisements.add(ad1);
        advertisements.add(ad2);

        assertTrue(Advertisement.hasUniqueAdvertisementIds(advertisements), "Advertisements should have unique IDs.");

        Advertisement duplicateAd = new Advertisement(1, "Duplicate Corp", "Duplicate Ad Content", 300.0);
        advertisements.add(duplicateAd);

        assertFalse(Advertisement.hasUniqueAdvertisementIds(advertisements), "Duplicate advertisement IDs should violate the OCL rule.");
    }

    @Test
    public void testIssueContentRequirement() {
        Issue issueWithContent = new Issue(1, "Tech Layout", "Draft", LocalDate.now());
        Story story = new Story("S1", "Tech Innovations", "Content on AI", "Alice", LocalDate.now(), "Technology");
        Advertisement ad = new Advertisement(1, "Ad for Tech Gadgets", "Paid", true);

        issueWithContent.addStory(story);
        issueWithContent.addAdvertisement(ad);
        assertTrue(issueWithContent.mustHaveContent(), "Issue with stories and advertisements should satisfy the OCL rule.");

        Issue issueWithoutContent = new Issue(2, "Empty Layout", "Draft", LocalDate.now());
        assertFalse(issueWithoutContent.mustHaveContent(), "Issue with no stories or advertisements should violate the OCL rule.");
    }

    @Test
    public void testEditorApprovalRequirement() {
        Issue unapprovedIssue = new Issue(1, "Tech Monthly", "Draft", LocalDate.now());
        unapprovedIssue.setPublished(false);
        unapprovedIssue.setApproved(false);

        assertFalse(unapprovedIssue.editorApprovalRequired(), "Unapproved issue should violate the OCL rule.");

        Issue approvedIssue = new Issue(2, "Auto Weekly", "Published", LocalDate.now());
        approvedIssue.setPublished(true);
        approvedIssue.setApproved(true);

        assertTrue(approvedIssue.editorApprovalRequired(), "Approved issue should satisfy the OCL rule.");
    }

    @Test
    public void testProcessPayments() {
        AccountsDepartment accounts = new AccountsDepartment();
        Contributor contributor1 = new Contributor(1, "John Doe", "Writer", 100.0);
        Contributor contributor2 = new Contributor(2, "Jane Smith", "Photographer", 150.0);

        Issue issue = new Issue(1, "Final Layout", "Draft", LocalDate.now().minusDays(10));
        issue.addContributor(contributor1);
        issue.addContributor(contributor2);

        accounts.processPayments(issue);

        assertEquals("Processed", contributor1.getPaymentStatus(), "Payments for valid contributors should be processed.");
        assertEquals("Processed", contributor2.getPaymentStatus(), "Payments for valid contributors should be processed.");
    }

    @Test
    public void testCompileMagazineIssue() {
        Editor editor = new Editor();

        Issue issueWithUnfinalizedStory = new Issue(2, "Custom Layout", "Draft", LocalDate.now());
        Story unfinalizedStory = new Story("S1", "Unfinalized Story", false);
        Advertisement validAd = new Advertisement(1, "Ad for Electronics", "Paid", true);

        issueWithUnfinalizedStory.addStory(unfinalizedStory);
        issueWithUnfinalizedStory.addAdvertisement(validAd);
        editor.setCurrentIssue(issueWithUnfinalizedStory);

        assertNull(editor.compileMagazineIssue(), "Issue with unfinalized stories should not be compiled.");

        Issue validIssue = new Issue(3, "Premium Layout", "Draft", LocalDate.now());
        Story finalizedStory = new Story("S2", "Finalized Story", true);
        validIssue.addStory(finalizedStory);
        validIssue.addAdvertisement(validAd);
        editor.setCurrentIssue(validIssue);

        assertNotNull(editor.compileMagazineIssue(), "Valid issue with finalized stories should be compiled successfully.");
    }
}

