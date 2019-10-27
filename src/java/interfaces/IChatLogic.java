package interfaces;

public interface IChatLogic {
    void subscribe(IInputOutput handler);
    void processMessage(String message);
}
