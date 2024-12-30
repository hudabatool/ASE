/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author hudab
 */
public class Contributor {
    private int contributorId;
    private String name;
    private String role;
    private double balance;

    // Constructor
    public Contributor(int contributorId, String name, String role, double balance) {
        this.contributorId = contributorId;
        this.name = name;
        this.role = role;
        this.balance = balance;
    }

    // Getters and Setters
    public int getContributorId() { return contributorId; }
    public void setContributorId(int contributorId) { this.contributorId = contributorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}

