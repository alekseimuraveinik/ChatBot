package root;

import datasource.CloudStorageLoader;
import db.Database;
import interfaces.IChatLogic;
import interfaces.IDatabaseLoader;
import interfaces.IMessageProcessor;
import interfaces.IQuestionGettable;
import logic.ChatLogic;
import telegramLogic.MessagesProcessor;
import telegramLogic.TelegramIO;


public class EntryPoint{

    private static final String NEW_FORMAT_QUESTIONS_FILENAME = "new_format.txt";
    private static final String OLD_FORMAT_QUESTIONS_FILENAME = "questions.txt";

    public static void main(String[] args) {
        IDatabaseLoader dbLoader = new Database("firebase_api_key.json");
        IQuestionGettable cloudLoader = new CloudStorageLoader(dbLoader);
        IChatLogic logic = new ChatLogic(cloudLoader, dbLoader);

        IMessageProcessor processor = new MessagesProcessor(logic);

        TelegramIO io = new TelegramIO();

        processor.subscribe(io);
        io.subscribe(processor);

        io.init();
    }
}
