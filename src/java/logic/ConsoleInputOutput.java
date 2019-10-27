package logic;

import datamodel.ShortMessage;
import interfaces.IMessageHandler;
import interfaces.IMessageReceiver;

import java.util.Scanner;

public class ConsoleInputOutput implements IMessageReceiver, IMessageHandler {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void handle(Long userID, String message) {
        System.out.println(userID + ": " + message);
    }

    @Override
    public ShortMessage readLine(){
        return new ShortMessage(0L, sc.nextLine());
    }
}
