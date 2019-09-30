package logic;

import interfaces.IChatLogic;
import interfaces.IMessageHandler;

public class ChatLogic implements IChatLogic {
    private IMessageHandler handler;
    @Override
    public void subscribe(IMessageHandler handler) {
        this.handler = handler;
    }

    @Override
    public void processMessage(String message) {
        //Здесь логика обработки ответов пользователя
        handler.handle(message);
    }
}
