/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package system;

/**
 * @author HudaB_21554056 */
import models.Contributor;

public interface IPaymentProcessor {
    void processContributorPayment(int contributorID, double amount);
    void generateInvoice(int advertiserID, double amount);
}

