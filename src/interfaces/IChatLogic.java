package interfaces;

public interface IChatLogic {
    public void subscribe(IMessageHandler handler);
    public void processMessage(String message);
    public boolean hasQuestions();
}
