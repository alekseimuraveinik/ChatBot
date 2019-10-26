package auxiliary;

import interfaces.IMessageHandler;

public class MessageHolder implements IMessageHandler {

    private String lastReceivedMessage;
    private boolean terminated = false;

    @Override
    public void handle(String message) {
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
