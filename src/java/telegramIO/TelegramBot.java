package telegramIO;

import interfaces.IMessageHandler;
import interfaces.IMessageReader;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Dictionary;

public class TelegramBot extends TelegramLongPollingBot{
    @Override
    public void onUpdateReceived(Update upd){
        Message msg = upd.getMessage(); // Это нам понадобится
        Long chatId = msg.getChatId();
        String txt = msg.getText();
        System.out.println(txt);
        sendMsg(chatId, txt);
    }

    @Override
    public String getBotUsername(){
        return "MyRPGGameBot";
    }

    @Override
    public String getBotToken(){
        return "959156287:AAE1iEBjxGFKbtqF_i0Zn4iqdsvJqvY7q4w";
    }

    private void sendMsg(Long chatId, String text) {
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
