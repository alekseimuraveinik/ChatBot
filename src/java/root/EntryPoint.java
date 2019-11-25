package root;

import datasource.CloudStorageLoader;
import datasource.FileReader;
import db.Database;
import logic.Callboard;
import logic.IChatLogic;
import db.IDatabaseLoader;
import telegramLogic.IMessageProcessor;
import datasource.IQuestionGettable;
import logic.ChatLogic;
import telegramLogic.MessagesProcessor;
import io.TelegramIO;


public class EntryPoint{
    public static void main(String[] args) {
        String SUCCESS_MESSAGE = "Success!";
        String ERROR_MESSAGE = "Connection error!";
        String filename = "telegram_data";
        String apiKeyFilename = "firebase_api_key.json";


        IDatabaseLoader dbLoader = new Database(apiKeyFilename);
        IQuestionGettable cloudLoader = new CloudStorageLoader(dbLoader);
        Callboard callboard = new Callboard(dbLoader);

        IChatLogic logic = new ChatLogic(cloudLoader, callboard);

        IMessageProcessor processor = new MessagesProcessor(logic, dbLoader);

        try (FileReader reader = new FileReader(filename)) {
            String botName = reader.readLine();
            String botToken = reader.readLine();

            TelegramIO io = new TelegramIO(botName, botToken);

            processor.subscribe(io);
            io.subscribe(processor);

            io.init();
            System.out.println(SUCCESS_MESSAGE);

        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(ERROR_MESSAGE);
        }
    }
}
