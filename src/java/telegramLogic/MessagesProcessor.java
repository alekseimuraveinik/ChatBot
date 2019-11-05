package telegramLogic;

import datamodel.ShortMessage;
import datamodel.UserID;
import interfaces.IChatLogic;
import interfaces.IMessageHandler;
import interfaces.IMessageProcessor;
import interfaces.IPlayer;
import logic.Player;

import java.util.HashMap;

public class MessagesProcessor implements IMessageProcessor{
    private HashMap<Long, IPlayer> logicDict;
    private IChatLogic logic;
    private IMessageHandler handler;

    public MessagesProcessor(IChatLogic logic){
        this.logic = logic;
        logicDict = new HashMap<>();
    }

    @Override
    public void subscribe(IMessageHandler handler){
        this.handler = handler;
    }

    @Override
    public void processMessage(ShortMessage message){
        if (!logicDict.containsKey(message.chatID)){
            IPlayer player = new Player(logic, new UserID(message.chatID));
            player.subscribe(handler);
            logicDict.put(message.chatID, player);
        }
        else {
            logicDict.get(message.chatID).processMessage(message.text);
        }
    }
}
