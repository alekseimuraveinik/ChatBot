package logic;

import datamodel.Node;
import datamodel.UserID;

public class Player implements IPlayer {
    private Node currentNode;
    private UserID chatId;
    private IChatLogic logic;
    private IMessageHandler handler;

    public Player(IChatLogic logic, UserID chatId){
        this.logic = logic;
        this.chatId = chatId;
        currentNode = logic.getRoot();
    }

    public Player(){

    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public UserID getChatId() {
        return chatId;
    }

    public void setChatId(UserID chatId) {
        this.chatId = chatId;
    }

    public void setLogic(IChatLogic logic) {
        this.logic = logic;
    }

    @Override
    public void processMessage(String message) {
        logic.processMessage(message, this, currentNode);
    }

    @Override
    public void handle(String processedMessage){
        handler.handle(chatId, processedMessage);
    }

    @Override
    public void subscribe(IMessageHandler handler) {
        this.handler = handler;
        handler.handle(chatId, currentNode.getQuestionContent());
    }

    @Override
    public void changeState(Node currentNode) {
        this.currentNode = currentNode;
    }
}
