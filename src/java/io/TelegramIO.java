package io;

import datamodel.UserID;
import logic.IMessageHandler;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import telegramLogic.IMessageProcessor;
import telegramLogic.TelegramBot;

public class TelegramIO implements IMessageHandler {

    private TelegramBot bot;

    public TelegramIO(String botName, String botToken){
        ApiContextInitializer.init();
        bot = new TelegramBot(botName, botToken);
    }

    public void subscribe(IMessageProcessor processor){
        bot.subscribe(processor);
    }

    public void init() throws TelegramApiRequestException {
        new TelegramBotsApi().registerBot(bot);
    }

    @Override
    public synchronized void handle(UserID userID, String message) {
        bot.sendMsg(userID.id, message);
    }
}
