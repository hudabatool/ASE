/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 * @author HudaB_21554056 */
import editor.Editor;
import java.util.logging.Logger;

public class RejectStoryCommand implements Command {
    private static final Logger logger = Logger.getLogger(RejectStoryCommand.class.getName());
    private Editor editor;
    private String rejectedStory;

    public RejectStoryCommand(Editor editor, String rejectedStory) {
        if (editor == null) {
            throw new IllegalArgumentException("Editor cannot be null.");
        }
        if (rejectedStory == null || rejectedStory.isEmpty()) {
            throw new IllegalArgumentException("Rejected story cannot be null or empty.");
        }
        this.editor = editor;
        this.rejectedStory = rejectedStory;
    }

    @Override
    public void execute() {
        try {
            logger.info("Executing RejectStoryCommand for story: " + rejectedStory);
            editor.rejectStory(rejectedStory);
        } catch (Exception e) {
            logger.severe("Error executing RejectStoryCommand: " + e.getMessage());
        }
    }

    public void undo() {
        try {
            logger.info("Undoing RejectStoryCommand for story: " + rejectedStory);
            editor.undoRejectStory(rejectedStory);
        } catch (Exception e) {
            logger.severe("Error undoing RejectStoryCommand: " + e.getMessage());
        }
    }
}
