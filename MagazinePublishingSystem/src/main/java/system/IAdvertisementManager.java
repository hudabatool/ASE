/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package system;

/**
 *
 * @author hudab
 */
import models.Advertisement;

public interface IAdvertisementManager {
    void submitAdvertisement(Advertisement ad);
    Advertisement getAdvertisementDetails(int adID);
    void editAdvertisement(int adID, Advertisement newDetails);
}

