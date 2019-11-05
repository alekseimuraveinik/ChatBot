package interfaces;

import datamodel.Node;

public interface IChatLogic {
    void processMessage(String message, IPlayer player, Node currentNode);
    Node getRoot();
}
