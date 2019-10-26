package root;

import datasource.CloudStorageLoader;
import datasource.QuestionLoader;
import interfaces.IChatLogic;
import interfaces.IQuestionGettable;
import logic.ChatLogic;
import logic.ConsoleInputOutput;

public class EntryPoint{

    public static void main(String[] args) {
        String newFilename = "newformat.txt";

        IQuestionGettable localLoader = new QuestionLoader(newFilename);
        IQuestionGettable cloudLoader = new CloudStorageLoader();

        try {
            IChatLogic logic = new ChatLogic(cloudLoader);
            ConsoleInputOutput io = new ConsoleInputOutput();

            logic.subscribe(io);

            while (true){
                String message = io.readLine();
                logic.processMessage(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
