package telegramLogic;

import datamodel.ShortMessage;
import logic.IMessageHandler;

public interface IMessageProcessor {
    void processMessage(ShortMessage message);
}
