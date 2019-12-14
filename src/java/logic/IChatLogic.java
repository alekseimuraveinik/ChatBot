package logic;

import datamodel.Graph;
import datamodel.GraphNode;
import datamodel.QuestMessage;

public interface IChatLogic {
    void processMessage(String message, IPlayer player);
    QuestMessage getNewPlayerMessage(IPlayer player);
}
