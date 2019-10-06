package interfaces;

public interface IChatLogic {
    void subscribe(IMessageHandler handler);
    void processMessage(String message);
}
