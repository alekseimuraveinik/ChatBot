package logic;

import datamodel.Graph;

public interface IChatLogic {
    void processMessage(String message, IPlayer player, Graph currentNode);
    Graph getRoot();
}
