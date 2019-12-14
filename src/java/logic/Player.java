package logic;

import datamodel.GraphNode;
import datamodel.PlayerState;
import datamodel.QuestMessage;
import datamodel.UserID;
import org.springframework.context.ApplicationContext;

public class Player implements IPlayer {
    private UserID chatId;
    private PlayerState state;

    private IMessageHandler handler;
    private IChatLogic logic;
    private IMessageLogic graphWalker;

    public Player(UserID chatId, PlayerState state) {
        this.chatId = chatId;
        this.state = state;
    }

    public void set(IChatLogic logic, IMessageLogic graphWalker){
        this.logic = logic;
        this.graphWalker = graphWalker;
    }

    public Player(){

    }

    public UserID getChatId() {
        return chatId;
    }

    @Override
    public void processMessage(String message) {
        logic.processMessage(message, this);
    }

    @Override
    public void switchToDefaultLogic() {
        state.switchLogic(graphWalker);
    }

    public PlayerState getState() { return state; }

    @Override
    public void handle(QuestMessage processedMessage){
        if (processedMessage != null && !"".equals(processedMessage))
            handler.handle(chatId, processedMessage);
    }

    @Override
    public void subscribe(IMessageHandler handler, Boolean isNewPlayer) {
        this.handler = handler;
        if (isNewPlayer) {
            handler.handle(chatId, logic.getNewPlayerMessage(this));
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

    public void setState(PlayerState state) {
        this.state = state;
    }
}
