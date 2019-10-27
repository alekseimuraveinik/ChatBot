package interfaces;

import datamodel.ShortMessage;

public interface IMessageReceiver {
    ShortMessage readLine() throws InterruptedException;
}
