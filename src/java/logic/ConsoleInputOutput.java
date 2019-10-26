package logic;

import interfaces.IMessageHandler;
import interfaces.IMessageReader;

import java.util.Scanner;

public class ConsoleInputOutput implements IMessageHandler, IMessageReader {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void handle(String message) {
        System.out.println(message);
    }

    @Override
    public String readLine(){
        return sc.nextLine();
    }
}
