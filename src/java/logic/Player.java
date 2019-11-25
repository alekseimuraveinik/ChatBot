package logic;

import datamodel.Graph;
import datamodel.PlayerInventory;
import datamodel.UserID;

public class Player implements IPlayer {
    private Graph playerGraph;
    private PlayerInventory playerInventory;
    private UserID chatId;
    private IChatLogic logic;
    private IMessageHandler handler;
    private static final String HELLO_MESSAGE = "Добро пожаловать в текстовый РПГ мир";

    public Player(IChatLogic logic, UserID chatId){
        this.logic = logic;
        this.chatId = chatId;
        playerGraph = logic.getRoot();
        playerInventory = new PlayerInventory();
    }

    public Player(){

    }

    public PlayerInventory getPlayerInventory() { return playerInventory; }

    public void setPlayerInventory(PlayerInventory playerInventory) { this.playerInventory = playerInventory; }

    public Graph getPlayerGraph() {
        return playerGraph;
    }

    public void setPlayerGraph(Graph playerGraph) {
        this.playerGraph = playerGraph;
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
        logic.processMessage(message, this, playerGraph);
    }

    @Override
    public void handle(String processedMessage){
        handler.handle(chatId, processedMessage);
    }

    @Override
    public void subscribe(IMessageHandler handler, Boolean isNewPlayer) {
        this.handler = handler;
        if (isNewPlayer) {
            handler.handle(chatId, HELLO_MESSAGE + playerGraph.formattedContentAndNextNodes());
        }
    }

    @Override
    public void changeState(Graph currentNode) {
        this.playerGraph = currentNode;
    }
}
