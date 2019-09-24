package root;

import interfaces.IChatLogic;
import interfaces.IMessageHandler;
import logic.ChatLogic;
import logic.ConsoleInputOutput;
import logic.ParseQuestList;

public class EntryPoint {
    public static void main(String[] args)
    {
        /*IChatLogic logic = new ChatLogic();
        ConsoleInputOutput io = new ConsoleInputOutput();

        logic.subscribe(io);

        while (true){
            String message = io.readLine();
            logic.processMessage(message);
        }*/

        ParseQuestList a = new ParseQuestList();
        a.ParseQuests("question.txt");
    }
}
