package root;

import datamodel.ShortMessage;
import interfaces.IInputOutput;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import sun.plugin2.message.Message;
import telegramLogic.TelegramBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import datasource.CloudStorageLoader;
import datasource.QuestionLoader;
import interfaces.IChatLogic;
import interfaces.IQuestionGettable;
import logic.ChatLogic;
import logic.ConsoleInputOutput;
import telegramLogic.TelegramIO;

import java.util.HashMap;
import java.util.HashSet;

public class EntryPoint{

    public static void main(String[] args) {

        String newFilename = "newformat.txt";

        IQuestionGettable localLoader = new QuestionLoader(newFilename);
        IQuestionGettable cloudLoader = new CloudStorageLoader();

        HashMap<Long, IChatLogic> logicDict = new HashMap<>();


        try {
            IInputOutput io = new TelegramIO();
            while (true){
                ShortMessage message = io.readLine();

                if (!logicDict.containsKey(message.chatID)){
                    IChatLogic logic = new ChatLogic(cloudLoader, message.chatID);
                    logic.subscribe(io);
                    Long chatID = message.chatID;
                    logicDict.put(chatID, logic);
                }
                else {
                    logicDict.get(message.chatID).processMessage(message.text);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
