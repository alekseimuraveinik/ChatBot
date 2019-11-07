package telegramLogic;

import datamodel.ShortMessage;
import logic.IMessageHandler;

public interface IMessageProcessor {
    void subscribe(IMessageHandler handler);
    void processMessage(ShortMessage message);
}
