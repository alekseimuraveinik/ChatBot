package root;

import datamodel.ShortMessage;
import datasource.CloudStorageLoader;
import db.Database;
import interfaces.IChatLogic;
import interfaces.IQuestionGettable;
import logic.ChatLogic;
import telegramLogic.TelegramIO;
import telegramLogic.UserThread;

import java.util.HashMap;


public class EntryPoint{

    private static final String NEW_FORMAT_QUESTIONS_FILENAME = "new_format.txt";
    private static final String OLD_FORMAT_QUESTIONS_FILENAME = "questions.txt";

    public static void main(String[] args) {

        IQuestionGettable cloudLoader = new CloudStorageLoader();

        HashMap<Long, UserThread> logicDict = new HashMap<>();

        TelegramIO io = new TelegramIO();

        while (true){
            try {

                ShortMessage message = io.readLine();

                if (!logicDict.containsKey(message.chatID)){
                    IChatLogic logic = new ChatLogic(cloudLoader, message.chatID);
                    UserThread userThread = new UserThread(logic, io);
                    logicDict.put(message.chatID, userThread);
                }
                else {
                    logicDict.get(message.chatID).transferControl(message.text);
                }

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

    }
}
