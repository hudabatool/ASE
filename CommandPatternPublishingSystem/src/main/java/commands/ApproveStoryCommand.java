/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 * @author HudaB_21554056 */
import editor.Editor;
import java.util.logging.Logger;

public class ApproveStoryCommand implements Command {
    private static final Logger logger = Logger.getLogger(ApproveStoryCommand.class.getName());
    private final Editor receiver;

    public ApproveStoryCommand(Editor receiver) {
        if (receiver == null) {
            throw new IllegalArgumentException("Receiver cannot be null.");
        }
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        try {
            logger.info("Executing ApproveStoryCommand...");
            receiver.approveStory();
        } catch (Exception e) {
            logger.severe("Error executing ApproveStoryCommand: " + e.getMessage());
        }
    }
}
