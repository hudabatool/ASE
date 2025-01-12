/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

/**
 * @author HudaB_21554056 */
import editor.Editor;
import models.Issue;
import publisher.Publisher;
import java.util.logging.Logger;
import java.time.LocalDateTime;

public class CompileIssueCommand implements Command {
	private static final Logger logger = Logger.getLogger(CompileIssueCommand.class.getName());
    private Editor receiver;
    private LocalDateTime timestamp;

    public CompileIssueCommand(Editor receiver) {
        this.receiver = receiver;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public void execute() {
    	if (receiver == null) {
            throw new IllegalStateException("Receiver is not set for CompileIssueCommand.");
        }

        logger.info("Executing CompileIssueCommand at " + timestamp);

        // Log the action before invoking the compile process
        logger.info("Invoking compileMagazineIssue...");

        // Now invoke the compileMagazineIssue method from the Editor
        Issue compiledIssue = receiver.compileMagazineIssue();

     // Now publish the compiled issue
        Publisher publisher = new Publisher();  
        publisher.publishIssue(compiledIssue);
    }
}
