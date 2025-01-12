/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Marketing;

/**
 * @author HudaB_21554056 */
import models.Advertisement;
import models.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class MarketingDepartment {
	private static final Logger LOGGER = Logger.getLogger(MarketingDepartment.class.getName());
    private List<Advertisement> advertisementsRepository;

    // Constructor
    public MarketingDepartment() {
        this.advertisementsRepository = new ArrayList<>();
    }

    // Submit Advertisement
    public boolean submitAdvertisement(Advertisement ad) {
        // Precondition: Payment information must be recorded before submission
        if (ad.getPaymentDetails() == null || ad.getPaymentDetails().isEmpty()) {
            LOGGER.warning("Payment information must be recorded before submission.");
            return false;
        }

        // Postcondition: Advertisement is added to the repository
        this.advertisementsRepository.add(ad);

        if (!this.advertisementsRepository.contains(ad)) {
            LOGGER.warning("Advertisement was not successfully added to the repository.");
            return false;
        }

        System.out.println("Advertisement submitted successfully.");
        return true;
    }

    // Getter for the repository
    public List<Advertisement> getAdvertisementsRepository() {
        return advertisementsRepository;
    }
}
