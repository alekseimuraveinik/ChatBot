package logic;

import datamodel.Graph;
import datamodel.PlayerInventory;

public interface IPlayer {
    void handle(String processedMessage);
    void subscribe(IMessageHandler handler, Boolean isNewPlayer);
    void changeState(Graph currentNode);
    void processMessage(String message);
    void setPlayerInventory(PlayerInventory playerInventory);
    PlayerInventory getPlayerInventory();
}
