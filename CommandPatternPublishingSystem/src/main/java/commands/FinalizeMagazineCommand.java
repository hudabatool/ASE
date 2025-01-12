/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 * @author HudaB_21554056 */
import publisher.Publisher;
import java.util.logging.Logger;

public class FinalizeMagazineCommand implements Command {
    private static final Logger logger = Logger.getLogger(FinalizeMagazineCommand.class.getName());
    private Publisher publisher;
    private String magazineIssue;

    public FinalizeMagazineCommand(Publisher publisher, String magazineIssue) {
        if (publisher == null) {
            throw new IllegalArgumentException("Publisher cannot be null.");
        }
        if (magazineIssue == null || magazineIssue.isEmpty()) {
            throw new IllegalArgumentException("Magazine issue cannot be null or empty.");
        }
        this.publisher = publisher;
        this.magazineIssue = magazineIssue;
    }

    @Override
    public void execute() {
        try {
            logger.info("Executing FinalizeMagazineCommand for issue: " + magazineIssue);
            publisher.finalizeIssue(magazineIssue);
        } catch (Exception e) {
            logger.severe("Error executing FinalizeMagazineCommand: " + e.getMessage());
        }
    }
    public void undo() {
        try {
            logger.info("Undoing FinalizeMagazineCommand for issue: " + magazineIssue);
            publisher.undoFinalizeIssue(magazineIssue);
        } catch (Exception e) {
            logger.severe("Error undoing FinalizeMagazineCommand: " + e.getMessage());
        }
    }
}
