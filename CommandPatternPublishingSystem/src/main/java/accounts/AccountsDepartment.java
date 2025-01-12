/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accounts;

/**
 * @author HudaB_21554056 */
import models.Contributor;
import system.IPaymentProcessor;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AccountsDepartment implements IPaymentProcessor {
    private static final Logger logger = Logger.getLogger(AccountsDepartment.class.getName());
    private HashMap<Integer, Contributor> contributors;
    private Map<String, Double> payments = new HashMap<>();

    public AccountsDepartment() {
        this.contributors = new HashMap<>();
    }

    public void addContributor(Contributor contributor) {
        contributors.put(contributor.getContributorId(), contributor);
        System.out.println("Contributor added: " + contributor.getName());
    }

    @Override
    public void processContributorPayment(int contributorID, double amount) {
        if (contributors.containsKey(contributorID)) {
            Contributor contributor = contributors.get(contributorID);
            contributor.setBalance(contributor.getBalance() + amount);
            System.out.println("Payment processed for contributor: " + contributor.getName());
        } else {
            System.out.println("Contributor not found for ID: " + contributorID);
        }
    }

    @Override
    public void generateInvoice(int advertiserID, double amount) {
        System.out.println("Invoice generated for Advertiser ID: " + advertiserID + ", Amount: " + amount);
    }
    
    public void processPayments() {
        logger.info("Processing payments...");
        try {
            payments.put("Contributor1", 500.0);
            payments.put("Contributor2", 300.0);
            System.out.println("Payments processed: " + payments);
        } catch (Exception e) {
            logger.severe("Error during payment processing: " + e.getMessage());
        }
    }

    public void undoPayments() {
        if (!payments.isEmpty()) {
            logger.info("Reversing payments...");
            payments.clear();
            System.out.println("Payments reversed.");
        } else {
            logger.warning("No payments to reverse.");
        }
    }
}

