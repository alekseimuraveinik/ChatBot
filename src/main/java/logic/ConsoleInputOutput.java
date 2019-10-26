package logic;

import interfaces.IMessageHandler;

import java.util.Scanner;

public class ConsoleInputOutput implements IMessageHandler {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void handle(String message) {
        System.out.println(message);
    }

    public String readLine(){
        return sc.nextLine();
    }
}
