package root;

import datasource.ParseQuestList;
import interfaces.IChatLogic;
import logic.ChatLogic;
import logic.ConsoleInputOutput;

public class EntryPoint{
    public static void main(String[] args)
    {
        IChatLogic logic = new ChatLogic(new ParseQuestList("questions.txt"));
        ConsoleInputOutput io = new ConsoleInputOutput();

        logic.subscribe(io);

        while (true){
            String message = io.readLine();
            logic.processMessage(message);
        }
    }
}
