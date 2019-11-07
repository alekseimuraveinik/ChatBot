package telegramLogic;

import datamodel.ShortMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TelegramBot extends TelegramLongPollingBot {
    private String botName;
    private String botToken;

    private IMessageProcessor processor;

    public TelegramBot(String botName, String botToken){
        this.botName = botName;
        this.botToken = botToken;
    }

    public void subscribe(IMessageProcessor processor){
        this.processor = processor;
    }

    @Override
    public synchronized void onUpdateReceived(Update upd){
        Message m = upd.getMessage();
        processor.processMessage(new ShortMessage(m.getChatId(), m.getText()));
    }

    @Override
    public String getBotUsername(){
        return botName;
    }

    @Override
    public String getBotToken(){
        return botToken;
    }

    public void sendMsg(Long chatId, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(chatId);
        s.setText(text);
        try {
            execute(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
