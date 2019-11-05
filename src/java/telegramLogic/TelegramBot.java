package telegramLogic;

import datamodel.ShortMessage;
import datasource.FileReader;
import interfaces.IMessageProcessor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class TelegramBot extends TelegramLongPollingBot {
    private static final String filename = "telegram_data";
    private static final String fileEncoding = "UTF-8";

    private String botName;
    private String botToken;

    private IMessageProcessor processor;

    TelegramBot(){
        try (FileReader reader = new FileReader(filename, fileEncoding)) {
            botName = reader.readLine();
            botToken = reader.readLine();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(IMessageProcessor processor){
        this.processor = processor;
    }

    @Override
    public void onUpdateReceived(Update upd){
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
