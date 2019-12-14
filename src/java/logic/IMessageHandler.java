package logic;

import datamodel.QuestMessage;
import datamodel.UserID;

public interface IMessageHandler {
    void handle(UserID userID, QuestMessage message);
}
