/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * @author HudaB_21554056 */
import java.util.ArrayList;
import java.util.logging.Logger;

public class Advertisement {
	private static final Logger LOGGER = Logger.getLogger(Advertisement.class.getName());
	
    private int adId;
    private String advertiserName;
    private String content;
    private double cost;
    private boolean isPaid;
    private boolean isFinalized;
    private String paymentDetails;

    // Constructor
    public Advertisement(int adId, String advertiserName, String content, String paymentDetails, boolean isPaid) {
        this.adId = adId;
        this.advertiserName = advertiserName;
        this.content = content;
        this.paymentDetails = paymentDetails;
        this.isPaid = isPaid;
        System.out.println("Advertisement created: " + advertiserName + " [ID: " + adId + "]");
    }
    
    public Advertisement(int adId, String advertiserName, String content, double cost) {
        this.adId = adId;
        this.advertiserName = advertiserName;
        this.content = content;
        this.cost = cost;
    }
    
    public Advertisement(int adId, String description, String paymentDetails) {
        this.adId = adId;
        this.content = description;
        this.paymentDetails = paymentDetails;
    }
    
    public Advertisement(int adId, String description, String paymentDetails, boolean isFinalized) {
        this.adId = adId;
        this.content = description;
        this.paymentDetails = paymentDetails;
        this.isFinalized = isFinalized;
    }

    // Getters and Setters
    public int getAdId() { return adId; }
    public void setAdId(int adId) {
        this.adId = adId;
        System.out.println("Ad ID updated to: " + adId);
    }

    public String getAdvertiserName() { return advertiserName; }
    public void setAdvertiserName(String advertiserName) {
        this.advertiserName = advertiserName;
        System.out.println("Advertiser name updated to: " + advertiserName);
    }

    public String getContent() { return content; }
    public void setContent(String content) {
        this.content = content;
        System.out.println("Content updated for Ad ID: " + adId);
    }

    public double getCost() { return cost; }
    public void setCost(double cost) {
        if (cost < 0) {
        	LOGGER.info("Cost cannot be negative.");
        }
        this.cost = cost;
        System.out.println("Cost updated for Ad ID: " + adId + " to " + cost);
    }
    
    public boolean isFinalized() {
        return isFinalized;
    }

    public void setFinalized(boolean finalized) {
        isFinalized = finalized;
    }
    
    public String getPaymentDetails() { return paymentDetails; }
    public void setPaymentDetails(String paymentDetails) { this.paymentDetails = paymentDetails; }
    
    // OCL Rule: Advertisements must be paid before publication.
    public boolean isPaymentRequiredBeforePublication() {
    	if (this.isPaid == true) {
            LOGGER.info("Advertisement is paid and ready.");
            return true;
        } else {
            LOGGER.warning("OCL Constraint Violated: Advertisement is not paid yet.");
            return false; // Advertisement is not paid
        }

    }

    // OCL Rule: Advertisers may place multiple ads, but each ad must have a unique identifier
    public static boolean hasUniqueAdvertisementIds(ArrayList<Advertisement> advertisements) {
        for (int i = 0; i < advertisements.size(); i++) {
            for (int j = i + 1; j < advertisements.size(); j++) {
                if (advertisements.get(i).getAdId() == advertisements.get(j).getAdId()) {
                    LOGGER.warning("Error: Advertisements have duplicate IDs.");
                    return false; // Duplicate IDs found
                }
            }
        }
        System.out.println("All advertisements have unique IDs.");
        return true;
    }
    
   // OCL Rule: Advertisements must have exactly one advertiser
    public boolean belongsToOneAdvertiser() {
        if (advertiserName != null && !advertiserName.isEmpty()) {
            System.out.println("Advertisement belongs to one advertiser.");
            return true;
        }
        LOGGER.info("Advertisement does not belong to an advertiser.");
        return false;
    }
}
