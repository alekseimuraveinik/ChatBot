package logic;

import datamodel.Node;
import datamodel.UserID;
import interfaces.IChatLogic;
import interfaces.IMessageHandler;
import interfaces.IPlayer;

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
