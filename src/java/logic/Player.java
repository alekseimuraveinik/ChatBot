package logic;

import datamodel.GraphNode;
import datamodel.PlayerInventory;
import datamodel.PlayerState;
import datamodel.UserID;

public class Player implements IPlayer {
    private UserID chatId;
    private IMessageHandler handler;
    public final PlayerState state;

    public Player(IChatLogic logic, UserID chatId){
        this.chatId = chatId;
        state = new PlayerState(logic.getRoot().getRoot(), new PlayerInventory(), logic);
    }

    public Player(){
        state = new PlayerState();
    }

    public UserID getChatId() {
        return chatId;
    }

    @Override
    public void processMessage(String message) {
        state.getLogic().processMessage(message, this, state.getCurrentNode());
    }

    public PlayerState getPlayerState() { return state; }

    @Override
    public void handle(String processedMessage){
        handler.handle(chatId, processedMessage);
    }

    @Override
    public void subscribe(IMessageHandler handler, Boolean isNewPlayer) {
        this.handler = handler;
        if (isNewPlayer) {
            handler.handle(chatId, state.getLogic().getNewPlayerMessage());
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
}
