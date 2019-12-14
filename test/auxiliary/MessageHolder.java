package auxiliary;

import datamodel.QuestMessage;
import datamodel.UserID;
import logic.IMessageHandler;

public class MessageHolder implements IMessageHandler {

    private String lastReceivedMessage;
    private boolean terminated = false;

    @Override
    public void handle(UserID userId, QuestMessage message) {
        if(!terminated){
            lastReceivedMessage = message.text;
            if(message.text.substring(message.text.length() - 1).equals("~"))
                terminated = true;
        }
    }

    public String getMessage(){
        return lastReceivedMessage;
    }
}
