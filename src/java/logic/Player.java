package logic;

import datamodel.GraphNode;
import datamodel.PlayerInventory;
import datamodel.UserID;

public class Player implements IPlayer {
    private GraphNode currentNode;
    private PlayerInventory playerInventory;
    private UserID chatId;
    private IChatLogic logic;
    private IMessageHandler handler;

    public Player(IChatLogic logic, UserID chatId){
        this.logic = logic;
        this.chatId = chatId;
        currentNode = logic.getRoot().getRoot();
        playerInventory = new PlayerInventory();
    }

    public Player(){

    }

    public UserID getChatId() {
        return chatId;
    }

    public void setLogic(IChatLogic logic){
        this.logic = logic;
    }

    public PlayerInventory getPlayerInventory() { return playerInventory; }

    public void setPlayerInventory(PlayerInventory playerInventory) { this.playerInventory = playerInventory; }

    @Override
    public void processMessage(String message) {
        logic.processMessage(message, this, currentNode);
    }

    @Override
    public void handle(String processedMessage){
        handler.handle(chatId, processedMessage);
    }

    @Override
    public void subscribe(IMessageHandler handler, Boolean isNewPlayer) {
        this.handler = handler;
        if (isNewPlayer) {
            handler.handle(chatId, logic.getNewPlayerMessage());
        }
    }

    @Override
    public void changeState(GraphNode currentNode) {
        this.currentNode = currentNode;
    }

    //ВСЕ ЧТО НАПИСАНО НИЖЕ ИСПОЛЬЗУЕТСЯ ДЛЯ СЕРИАЛИЗАЦИИ/ДЕСЕРИАЛИЗАЦИИ ОБЪЕКТА ПРИ РАБОТЕ С FIRESTORE

    public GraphNode getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(GraphNode currentNode) {
        this.currentNode = currentNode;
    }

    public void setChatId(UserID chatId) {
        this.chatId = chatId;
    }
}
