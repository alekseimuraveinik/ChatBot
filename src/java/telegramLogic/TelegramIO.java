package telegramLogic;

import datamodel.ShortMessage;
import interfaces.IMessageHandler;
import interfaces.IMessageReceiver;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramIO implements IMessageReceiver, IMessageHandler {
    private static final String SUCCESS_MESSAGE = "Success!";
    private static final String ERROR_MESSAGE = "Connection error!";

    private static final int MESSAGE_CHECKING_INTERVAL = 300;

    private TelegramBot bot;

    public TelegramIO(){
        ApiContextInitializer.init();
        bot = new TelegramBot();
        try {
            new TelegramBotsApi().registerBot(bot);
            System.out.println(SUCCESS_MESSAGE);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println(ERROR_MESSAGE);
        }
    }

    @Override
    public synchronized void handle(Long userID, String message) {
        bot.sendMsg(userID, message);
    }

    @Override
    public ShortMessage readLine() throws InterruptedException {
        while (bot.isEmpty())
            Thread.sleep(MESSAGE_CHECKING_INTERVAL);

        Message m = bot.removeInputMessage();
        return new ShortMessage(m.getChatId(), m.getText());
    }
}
