/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * @author HudaB_21554056 */
public class Advertisement {
    private int adId;
    private String advertiserName;
    private String content;
    private double cost;

    // Constructor
    public Advertisement(int adId, String advertiserName, String content, double cost) {
        this.adId = adId;
        this.advertiserName = advertiserName;
        this.content = content;
        this.cost = cost;
    }

    // Getters and Setters
    public int getAdId() { return adId; }
    public void setAdId(int adId) { this.adId = adId; }

    public String getAdvertiserName() { return advertiserName; }
    public void setAdvertiserName(String advertiserName) { this.advertiserName = advertiserName; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
}
