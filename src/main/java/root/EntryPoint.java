package root;

import datasource.QuestionLoader;
import interfaces.IChatLogic;
import interfaces.IQuestionGettable;
import logic.ChatLogic;
import logic.ConsoleInputOutput;

public class EntryPoint{
    public static void main(String[] args)
    {
        String newFilename = "newformat.txt";

        IQuestionGettable loader = new QuestionLoader(newFilename);
        IChatLogic logic = new ChatLogic(loader);
        ConsoleInputOutput io = new ConsoleInputOutput();

        logic.subscribe(io);

        while (true){
            String message = io.readLine();
            logic.processMessage(message);
        }
    }
}
