package telegramLogic;

import datamodel.ShortMessage;
import datamodel.UserID;
import db.IDatabaseLoader;
import logic.IMessageHandler;
import logic.IPlayer;
import logic.Player;
import logic.State;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import root.SpringConfiguration;

import java.util.HashMap;


public class MessageProcessor implements IMessageProcessor{

    private State state;
    private ApplicationContext context;

    public MessageProcessor(State state, ApplicationContext context){
        this.state = state;
        this.context = context;
    }

    @Override
    public void processMessage(ShortMessage message){
        Long chatID = message.chatID;
        if (!state.has(chatID)){
            Player player = context.getBean(Player.class, new UserID(chatID));
            state.add(chatID, player);
        }
        else {
            state.get(chatID).processMessage(message.text);
        }
    }
}
