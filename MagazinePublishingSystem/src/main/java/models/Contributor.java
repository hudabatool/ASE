/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * @author HudaB_21554056 */
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Contributor {
	private static final Logger LOGGER = Logger.getLogger(Contributor.class.getName());
	
    private int contributorId;
    private String name;
    private String role;
    private double balance;
    private String paymentStatus = "Not Processed";
    private LocalDate lastPaymentDate;

    // Constructor
    public Contributor(int contributorId, String name, String role, double balance) {
    	if (balance < 0) {
            LOGGER.severe("Initial balance cannot be negative for Contributor ID: " + contributorId);
            this.balance = 0; // Set to zero if invalid balance
        } else {
            this.balance = balance;
        }
    	this.contributorId = contributorId;
        this.name = name;
        this.role = role;
        this.paymentStatus = "Not Processed";
        System.out.println("Contributor created: " + name + " [ID: " + contributorId + "]");
    }
    
    // Default constructor
    public Contributor() {
    }
    
    // Postcondition: Marks the contributor's payment as processed.
    public void markPaymentProcessed(LocalDate paymentDate) {
    	if (paymentStatus.equals("Processed")) {
            LOGGER.warning("Payment for Contributor ID: " + contributorId + " has been processed.");
            return; // Skip processing if already done
        }
        if (balance < 0) {
            LOGGER.log(Level.WARNING, "Contributor [ID: {0}] has negative balance. Payment not processed.", contributorId);
            return; // Skip processing the payment
        }
        this.paymentStatus = "Processed";
        this.lastPaymentDate = paymentDate;
        this.balance = 0; // Assuming full payment clears the balance
        System.out.println("Payment processed for Contributor ID: " + contributorId + " on " + paymentDate);
    }
    
    // OCL Rule: Contributors must receive payment for their work within 30 days of publication
    public boolean isPaymentWithin30Days(LocalDate publicationDate) {
        if (lastPaymentDate == null) {
            return false;
        }
        long daysBetween = ChronoUnit.DAYS.between(publicationDate, lastPaymentDate);
        return daysBetween >= 0 && daysBetween <= 30;
    }

    // Getters and Setters
    public int getContributorId() {
        return contributorId;
    }

    public void setContributorId(int contributorId) {
        this.contributorId = contributorId;
        System.out.println("Contributor ID updated to: " + contributorId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("Contributor name updated to: " + name);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        System.out.println("Contributor role updated to: " + role);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
    	if (balance < 0) {
            LOGGER.severe("Balance cannot be negative for Contributor ID: " + contributorId + ". Current balance remains unchanged: " + this.balance);
            return;
        } else {
            this.balance = balance;
            System.out.println("Balance for Contributor ID " + contributorId + " updated to: " + balance);
        }
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
    
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(LocalDate lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
        System.out.println("Payment date updated for Contributor [ID: " + contributorId + "] to: " + lastPaymentDate);
    }
}

