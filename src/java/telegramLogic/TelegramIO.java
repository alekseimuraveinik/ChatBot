package telegramLogic;

import datamodel.UserID;
import interfaces.IMessageHandler;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramIO implements IMessageHandler {
    private static final String SUCCESS_MESSAGE = "Success!";
    private static final String ERROR_MESSAGE = "Connection error!";

    private static final int MESSAGE_CHECKING_INTERVAL = 300;

    private TelegramBot bot;

    public TelegramIO(){
        ApiContextInitializer.init();
        bot = new TelegramBot();
    }

    public void init(){
        try {
            new TelegramBotsApi().registerBot(bot);
            System.out.println(SUCCESS_MESSAGE);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println(ERROR_MESSAGE);
        }
    }

    public void subscribe(MessagesProcessor processor){
        bot.subscribe(processor);
    }

    @Override
    public synchronized void handle(UserID userID, String message) {
        bot.sendMsg(userID.id, message);
    }
}
