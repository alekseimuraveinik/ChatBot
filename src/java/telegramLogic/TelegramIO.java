package telegramLogic;

import datamodel.ShortMessage;
import interfaces.IMessageHandler;
import interfaces.IMessageReceiver;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramIO implements IMessageReceiver, IMessageHandler {
        private TelegramBot bot;

    public TelegramIO(){
        ApiContextInitializer.init();
        bot = new TelegramBot();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            System.out.println("Try");
            botsApi.registerBot(bot);
            System.out.println("Connected!");
        } catch (TelegramApiException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
    }

    @Override
    public void handle(Long userID, String message) {
        bot.sendMsg(userID, message);
    }

    @Override
    public ShortMessage readLine() throws InterruptedException {
        while (bot.inputMessages == null || bot.inputMessages.isEmpty()){
            Thread.sleep(300);
        }
        Message m = bot.inputMessages.remove();
        return new ShortMessage(m.getChatId(), m.getText());
    }
}
