package telegramLogic;

import interfaces.IChatLogic;
import interfaces.IMessageHandler;

public class UserThread {
    private IChatLogic logic;

    public UserThread(IChatLogic logic, IMessageHandler handler){
        this.logic = logic;
        logic.subscribe(handler);
    }

    public void transferControl(String message){
        new Thread(()->{
            logic.processMessage(message);
        }).start();
    }
}
