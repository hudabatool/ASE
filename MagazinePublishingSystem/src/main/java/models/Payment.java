/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * @author HudaB_21554056 */
import java.time.LocalDate;

public class Payment {
    private int contributorId;
    private double amount;
    private LocalDate dateProcessed;
    
    // Constructor to initialize Payment details
    public Payment(int contributorId, double amount, LocalDate dateProcessed) {
        this.contributorId = contributorId;
        this.amount = amount;
        this.dateProcessed = dateProcessed;
    }

    // Getter and setter methods
    public int getContributorId() {
        return contributorId;
    }

    public void setContributorId(int contributorId) {
        this.contributorId = contributorId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(LocalDate dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    // OCL Rule: Contributors must receive payment within 30 days of publication
    public boolean isPaymentTimely(LocalDate publicationDate) {
        LocalDate maxAllowedDate = publicationDate.plusDays(30);
        return !dateProcessed.isAfter(maxAllowedDate);
    }

    // Method to print payment details (for debugging purposes)
    public void printPaymentDetails() {
        System.out.println("Payment Details:");
        System.out.println("Contributor ID: " + contributorId);
        System.out.println("Amount: " + amount);
        System.out.println("Date Processed: " + dateProcessed);
    }
}
