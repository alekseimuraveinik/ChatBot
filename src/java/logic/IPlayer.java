package logic;

import datamodel.Node;

public interface IPlayer {
    void handle(String processedMessage);
    void subscribe(IMessageHandler handler);
    void changeState(Node currentNode);
    void processMessage(String message);
}
