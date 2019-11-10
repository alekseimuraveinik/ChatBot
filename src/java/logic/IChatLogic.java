package logic;

import datamodel.GraphNode;
import datamodel.Node;

public interface IChatLogic {
    void processMessage(String message, IPlayer player, GraphNode currentNode);
    GraphNode getRoot();
}
