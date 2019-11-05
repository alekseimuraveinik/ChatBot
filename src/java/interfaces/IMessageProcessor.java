package interfaces;

import datamodel.ShortMessage;

public interface IMessageProcessor {
    void subscribe(IMessageHandler handler);
    void processMessage(ShortMessage message);
}
