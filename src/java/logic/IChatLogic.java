package logic;

import datamodel.Graph;
import datamodel.GraphNode;

public interface IChatLogic {
    void processMessage(String message, IPlayer player, GraphNode currentNode);
    Graph getRoot();
    String getNewPlayerMessage(IPlayer player);
}
