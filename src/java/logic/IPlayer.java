package logic;

import datamodel.GraphNode;
import datamodel.PlayerInventory;
import datamodel.PlayerState;

public interface IPlayer {
    void handle(String processedMessage);
    void subscribe(IMessageHandler handler, Boolean isNewPlayer);
    void changePlayerLocation(GraphNode currentNode);
    void processMessage(String message);
    void switchToDefaultLogic();
    PlayerState getPlayerState();
}
