package logic;

import datamodel.GraphNode;
import datamodel.Node;
import datamodel.UserID;

public class Player implements IPlayer {
    private GraphNode currentNode;
    private UserID chatId;
    private IChatLogic logic;
    private IMessageHandler handler;
    private static final String HELLO_MESSAGE = "Добро пожаловать в текстовый РПГ мир";

    public Player(IChatLogic logic, UserID chatId){
        this.logic = logic;
        this.chatId = chatId;
        currentNode = logic.getRoot();
    }

    public Player(){

    }

    public GraphNode getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(GraphNode currentNode) {
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
    public void subscribe(IMessageHandler handler, Boolean isNewPlayer) {
        this.handler = handler;
        if (isNewPlayer) {
            handler.handle(chatId, HELLO_MESSAGE + currentNode.getFormattedContentAndNextNodes());
        }
    }

    @Override
    public void changeState(GraphNode currentNode) {
        this.currentNode = currentNode;
    }
}
