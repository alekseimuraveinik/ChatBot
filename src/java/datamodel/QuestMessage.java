package datamodel;

import java.util.ArrayList;

public class QuestMessage {
    public String text;
    public ArrayList<String> buttons = new ArrayList<>();

    public QuestMessage(String text){
        this.text = text;
    }

    public QuestMessage(String text, ArrayList<String> buttons){
        this.text = text;
        this.buttons = buttons;
    }

    public QuestMessage(){
        text = "";
    }

    public void addToText(String addedStr){
        text += addedStr;
    }

    public void addMessage(QuestMessage message){
        text += message.text;
        buttons.addAll(message.buttons);
    }
}
