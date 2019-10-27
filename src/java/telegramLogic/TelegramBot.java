package telegramLogic;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.LinkedList;
import java.util.Queue;

public class TelegramBot extends TelegramLongPollingBot {
    public Queue<Message> inputMessages;

    TelegramBot(){
        inputMessages = new LinkedList<>();
    }

    @Override
    public void onUpdateReceived(Update upd){
        inputMessages.add(upd.getMessage());
    }

    @Override
    public String getBotUsername(){
        return "MyRPGGameBot";
    }

    @Override
    public String getBotToken(){
        return "959156287:AAE1iEBjxGFKbtqF_i0Zn4iqdsvJqvY7q4w";
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
