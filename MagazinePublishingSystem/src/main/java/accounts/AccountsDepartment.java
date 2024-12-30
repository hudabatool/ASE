/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accounts;

/**
 *
 * @author hudab
 */
import models.Contributor;
import system.IPaymentProcessor;

import java.util.HashMap;

public class AccountsDepartment implements IPaymentProcessor {
    private HashMap<Integer, Contributor> contributors;

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
}

