package logic;

import datamodel.GraphNode;
import datamodel.PlayerInventory;
import datamodel.PlayerState;
import datamodel.UserID;

public class Player implements IPlayer {
    private UserID chatId;
    private IMessageHandler handler;
    public PlayerState state = new PlayerState();

    public Player(IChatLogic logic, UserID chatId){
        this.chatId = chatId;
        state = new PlayerState(logic.getGraph().getRoot(), new PlayerInventory(), logic);
    }

    public Player(){

    }

    public UserID getChatId() {
        return chatId;
    }

    @Override
    public void processMessage(String message) {
        state.logic().processMessage(message, this, state.getCurrentNode());
    }

    public PlayerState getState() { return state; }

    @Override
    public void handle(String processedMessage){
        handler.handle(chatId, processedMessage);
    }

    @Override
    public void subscribe(IMessageHandler handler, Boolean isNewPlayer) {
        this.handler = handler;
        if (isNewPlayer) {
            handler.handle(chatId, state.logic().getNewPlayerMessage(this));
        }
    }

    @Override
    public void changeState(GraphNode currentNode) {
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
