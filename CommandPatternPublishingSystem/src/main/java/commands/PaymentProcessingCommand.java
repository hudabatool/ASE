/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 * @author HudaB_21554056 */
import accounts.AccountsDepartment;
import java.util.logging.Logger;

public class PaymentProcessingCommand implements Command {
    private static final Logger logger = Logger.getLogger(PaymentProcessingCommand.class.getName());
    private AccountsDepartment accountsDepartment;

    public PaymentProcessingCommand(AccountsDepartment accountsDepartment) {
        if (accountsDepartment == null) {
            throw new IllegalArgumentException("AccountsDepartment cannot be null.");
        }
        this.accountsDepartment = accountsDepartment;
    }

    @Override
    public void execute() {
        try {
            logger.info("Executing PaymentProcessingCommand...");
            accountsDepartment.processPayments();
        } catch (Exception e) {
            logger.severe("Error executing PaymentProcessingCommand: " + e.getMessage());
        }
    }

    public void undo() {
        try {
            logger.info("Undoing PaymentProcessingCommand...");
            accountsDepartment.undoPayments();
        } catch (Exception e) {
            logger.severe("Error undoing PaymentProcessingCommand: " + e.getMessage());
        }
    }
}