package root;

import datasource.QuestionLoader;
import interfaces.IChatLogic;
import logic.ChatLogic;
import logic.ConsoleInputOutput;

public class EntryPoint{
    public static void main(String[] args)
    {
        String filename = "questions.txt";
        QuestionLoader loader = new QuestionLoader(filename);
        IChatLogic logic = new ChatLogic(loader);
        ConsoleInputOutput io = new ConsoleInputOutput();

        logic.subscribe(io);

        while (true){
            String message = io.readLine();
            logic.processMessage(message);
        }
    }
}
