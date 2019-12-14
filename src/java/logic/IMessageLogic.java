package logic;

import datamodel.QuestMessage;

public interface IMessageLogic {
    QuestMessage getMessageAnswer(IPlayer player, String message);
    QuestMessage getHelloMessage(IPlayer player);
    QuestMessage processCommand(IPlayer player, String command);
}
