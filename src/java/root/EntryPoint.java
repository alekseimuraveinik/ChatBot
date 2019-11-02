package root;

import datasource.CloudStorageLoader;
import interfaces.IChatLogic;
import interfaces.IQuestionGettable;
import logic.ChatLogic;
import telegramLogic.MessagesProcessor;
import telegramLogic.TelegramIO;


public class EntryPoint{

    private static final String NEW_FORMAT_QUESTIONS_FILENAME = "new_format.txt";
    private static final String OLD_FORMAT_QUESTIONS_FILENAME = "questions.txt";

    public static void main(String[] args) {

        IQuestionGettable cloudLoader = new CloudStorageLoader();

        IChatLogic logic = new ChatLogic(cloudLoader);

        MessagesProcessor processor = new MessagesProcessor(logic);

        TelegramIO io = new TelegramIO();

        processor.subscribe(io);
        io.subscribe(processor);

        io.init();
    }
}
