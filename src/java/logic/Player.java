package logic;

import datamodel.Node;
import interfaces.IChatLogic;
import interfaces.IMessageHandler;
import interfaces.IPlayer;

public class Player implements IPlayer {
    private Node currentNode;
    private Long chatId;
    private IChatLogic logic;
    private IMessageHandler handler;

    public Player(IChatLogic logic, Long chatId){
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
