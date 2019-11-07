package root;

import datasource.CloudStorageLoader;
import db.Database;
import logic.Callboard;
import logic.IChatLogic;
import db.IDatabaseLoader;
import telegramLogic.IMessageProcessor;
import datasource.IQuestionGettable;
import logic.ChatLogic;
import telegramLogic.MessagesProcessor;
import telegramLogic.TelegramIO;


public class EntryPoint{

    public static void main(String[] args) {
        String apiKeyFilename = "firebase_api_key.json";

        IDatabaseLoader dbLoader = new Database(apiKeyFilename);
        IQuestionGettable cloudLoader = new CloudStorageLoader(dbLoader);
        Callboard callboard = new Callboard(dbLoader);

        IChatLogic logic = new ChatLogic(cloudLoader, callboard);

        IMessageProcessor processor = new MessagesProcessor(logic);

        TelegramIO io = new TelegramIO();

        processor.subscribe(io);
        io.subscribe(processor);

        io.init();
    }
}
