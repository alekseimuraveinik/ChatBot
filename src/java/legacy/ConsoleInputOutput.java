package legacy;

import datamodel.ShortMessage;
import datamodel.UserID;
import interfaces.IMessageHandler;
import interfaces.IMessageReceiver;

import java.util.Scanner;

public class ConsoleInputOutput implements IMessageReceiver, IMessageHandler {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void handle(UserID userID, String message) {
        System.out.println(userID.id + ": " + message);
    }

    @Override
    public ShortMessage readLine(){
        return new ShortMessage(0L, sc.nextLine());
    }
}
