package logic;

import datamodel.GraphNode;
import datamodel.PlayerInventory;
import datamodel.PlayerState;
import datamodel.QuestMessage;

public interface IPlayer {
    void handle(QuestMessage processedMessage);
    void subscribe(IMessageHandler handler, Boolean isNewPlayer);
    void changePlayerLocation(GraphNode currentNode);
    void processMessage(String message);
    void switchToDefaultLogic();
    PlayerState getState();
}
