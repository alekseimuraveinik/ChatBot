package auxiliary;

import interfaces.IInputOutput;

/*public class MessageHolder implements IInputOutput {

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
}*/
