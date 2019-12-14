package telegramLogic;

import datamodel.QuestMessage;
import datamodel.ShortMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


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
        if(upd.hasMessage()) {
            if (upd.getMessage().hasText()) {
                Message m = upd.getMessage();
                processor.processMessage(new ShortMessage(m.getChatId(), m.getText()));
            }
        } else if(upd.hasCallbackQuery()){
            CallbackQuery c = upd.getCallbackQuery();
            processor.processMessage(new ShortMessage(c.getMessage().getChatId(), c.getData()));
        }
    }

    @Override
    public String getBotUsername(){
        return botName;
    }

    @Override
    public String getBotToken(){
        return botToken;
    }

    public void sendMsg(Long chatId, QuestMessage message) {
        SendMessage s = new SendMessage();
        s.setChatId(chatId);
        s.setText(message.text);
        setKeyboard(message, s);

        try {
            execute(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    private void setKeyboard(QuestMessage message, SendMessage s){
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (String buttName : message.buttons){
            ArrayList<InlineKeyboardButton> row = new ArrayList<>();

            row.add(new InlineKeyboardButton()
                    .setText(buttName)
                    .setCallbackData(buttName));
            rowList.add(row);
        }
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.setKeyboard(rowList);

        s.setReplyMarkup(keyboard);
    }
}
