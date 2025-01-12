/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accounts;

/**
 * @author HudaB_21554056 */
import models.*;
import system.IPaymentProcessor;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountsDepartment implements IPaymentProcessor {
    private static final Logger LOGGER = Logger.getLogger(AccountsDepartment.class.getName());
    private ConcurrentHashMap<Integer, Contributor> contributors = new ConcurrentHashMap<>();

    // Add Contributor to the system
    public boolean addContributor(Contributor contributor) {
        if (contributor == null) {
            LOGGER.log(Level.WARNING, "Cannot add a null Contributor.");
            return false;
        }
        if (contributors.containsKey(contributor.getContributorId())) {
            LOGGER.log(Level.WARNING, "Contributor with ID {0} already exists.", contributor.getContributorId());
            return false;
        }
        contributors.put(contributor.getContributorId(), contributor);
        System.out.println("Contributor added: " + contributor.getName());
        return true;
    }

    public String processPayments(Issue issue) {
        if (issue == null) {
            LOGGER.log(Level.SEVERE, "Issue cannot be null.");
            return "Issue is null";
        }

        List<Contributor> issueContributors = issue.getContributors();
        if (issueContributors.isEmpty()) {
            LOGGER.log(Level.WARNING, "No contributors available in Issue ID: {0}.", issue.getIssueId());
            return "No contributors";
        }

        LocalDate publicationDate = issue.getPublicationDate();
        if (publicationDate == null) {
            LOGGER.log(Level.SEVERE, "Publication date is missing for Issue ID: {0}.", issue.getIssueId());
            return "Publication date missing";
        }

        boolean allPaymentsSuccessful = true;

        for (Contributor contributor : issueContributors) {
            double paymentAmount = contributor.getBalance();

            if (paymentAmount <= 0) {
                LOGGER.log(Level.WARNING, "Invalid balance for Contributor ID: {0}. Skipping payment.", contributor.getContributorId());
                contributor.setPaymentStatus("Not Processed"); // Explicitly set status
                allPaymentsSuccessful = false;
                continue;
            }else {contributor.setPaymentStatus("Processed");}
            processContributorPayment(contributor.getContributorId(), paymentAmount);

            if (!contributor.isPaymentWithin30Days(publicationDate)) {
                LOGGER.log(Level.WARNING, "Payment for Contributor ID: {0} was not within 30 days of publication.", contributor.getContributorId());
                allPaymentsSuccessful = false; // Mark as partially processed
            }
        }

        if (!allPaymentsSuccessful) {
            return "Partially Processed";
        }

        System.out.println("All payments processed for Issue ID: " + issue.getIssueId());
        return "Processed";
    }

    @Override
    public void processContributorPayment(int contributorId, double amount) {
        if (amount <= 0) {
            LOGGER.log(Level.WARNING, "Invalid payment amount: {0} for Contributor ID: {1}.", new Object[]{amount, contributorId});
            return;
        }
        
        Contributor contributor = contributors.get(contributorId);
        if (contributor == null) {
            LOGGER.log(Level.WARNING, "Contributor with ID {0} does not exist.", contributorId);
            return;
        }
        
        if (contributor.getBalance() < amount) {
            LOGGER.log(Level.WARNING, "Insufficient balance for Contributor ID: {0}. Payment skipped.", contributorId);
            contributor.setPaymentStatus("Insufficient Balance"); // Optional for tracking
            return;
        }

        contributor.setBalance(contributor.getBalance() - amount);
        contributor.markPaymentProcessed(LocalDate.now()); // Sets the payment date
        contributor.setPaymentStatus("Processed");
        LOGGER.log(Level.INFO, "Payment of ${0} processed for Contributor ID: {1}.", new Object[]{amount, contributorId});
    }

    @Override
    public void generateInvoice(int advertiserID, double amount) {
        if (advertiserID <= 0) {
            LOGGER.log(Level.WARNING, "Invalid Advertiser ID: {0}.", advertiserID);
            return;
        }
        if (amount <= 0) {
            LOGGER.log(Level.WARNING, "Invalid invoice amount: ${0}.", amount);
            return;
        }
        System.out.println("Invoice generated for Advertiser ID: " + advertiserID + ", Amount: $" + amount);
    }
}

