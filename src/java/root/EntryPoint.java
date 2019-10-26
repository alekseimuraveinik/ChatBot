package root;

import telegramIO.TelegramBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import datasource.CloudStorageLoader;
import datasource.QuestionLoader;
import interfaces.IChatLogic;
import interfaces.IQuestionGettable;
import logic.ChatLogic;
import logic.ConsoleInputOutput;

public class EntryPoint{

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

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
