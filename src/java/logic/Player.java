package logic;

import datamodel.GraphNode;
import datamodel.PlayerInventory;
import datamodel.PlayerState;
import datamodel.UserID;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

public class Player implements IPlayer {
    private UserID chatId;

    private IMessageHandler handler;

    private ApplicationContext context;

    private PlayerState state;


    public Player(UserID chatId, PlayerState state, ApplicationContext context) {
        this.chatId = chatId;
        this.state = state;
        this.context = context;
    }

    public Player(){
        state = new PlayerState();
    }

    public UserID getChatId() {
        return chatId;
    }

    @Override
    public void processMessage(String message) {
        ChatLogic c = context.getBean(ChatLogic.class);
        c.processMessage(message, this);
    }

    @Override
    public void switchToDefaultLogic() {
        state.setMessageLogic(context.getBean(GraphWalkerLogic.class));
    }

    public PlayerState getPlayerState() { return state; }

    @Override
    public void handle(String processedMessage){
        if (processedMessage != null && processedMessage != "")
            handler.handle(chatId, processedMessage);
    }

    @Override
    public void subscribe(IMessageHandler handler, Boolean isNewPlayer) {
        this.handler = handler;
        if (isNewPlayer) {
            ChatLogic c = context.getBean(ChatLogic.class);
            handler.handle(chatId, c.getNewPlayerMessage(this));
        }
    }



    @Override
    public void changePlayerLocation(GraphNode currentNode) {
        this.state.setCurrentNode(currentNode);
    }

    //ВСЕ ЧТО НАПИСАНО НИЖЕ ИСПОЛЬЗУЕТСЯ ДЛЯ СЕРИАЛИЗАЦИИ/ДЕСЕРИАЛИЗАЦИИ ОБЪЕКТА ПРИ РАБОТЕ С FIRESTORE

    public void setChatId(UserID chatId) {
        this.chatId = chatId;
    }
}
