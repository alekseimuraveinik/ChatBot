package logic;

public interface IMessageLogic {
    String getMessageAnswer(IPlayer player, String message);
    String getHelloMessage(IPlayer player);
    String processCommand(IPlayer player, String command);
}
