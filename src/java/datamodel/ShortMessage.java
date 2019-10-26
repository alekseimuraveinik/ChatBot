package datamodel;

public class ShortMessage {
    public final Long chatID;
    public final String text;

    public ShortMessage(Long chatID, String text){
        this.chatID = chatID;
        this.text = text;
    }
}
