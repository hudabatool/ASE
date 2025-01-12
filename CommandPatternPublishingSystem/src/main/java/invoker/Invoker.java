/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package invoker;

/**
 * @author HudaB_21554056 */
import commands.Command;

public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void invoke() {
    	if (command != null) {
            System.out.println("Executing command...");
            command.execute();
        }
    }
}
