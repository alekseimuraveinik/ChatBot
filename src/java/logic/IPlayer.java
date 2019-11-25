package logic;

import datamodel.GraphNode;
import datamodel.PlayerInventory;

public interface IPlayer {
    void handle(String processedMessage);
    void subscribe(IMessageHandler handler, Boolean isNewPlayer);
    void changeState(GraphNode currentNode);
    void processMessage(String message);
    void setPlayerInventory(PlayerInventory playerInventory);
    PlayerInventory getPlayerInventory();
    GraphNode getCurrentNode();
}
