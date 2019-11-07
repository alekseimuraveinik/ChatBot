package auxiliary;

import datamodel.UserID;
import logic.IMessageHandler;

public class MessageHolder implements IMessageHandler {

    private String lastReceivedMessage;
    private boolean terminated = false;

    @Override
    public void handle(UserID userId, String message) {
        if(!terminated){
            lastReceivedMessage = message;
            if(message.substring(message.length() - 1).equals("~"))
                terminated = true;
        }
    }

    public String getMessage(){
        return lastReceivedMessage;
    }
}
