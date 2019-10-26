package interfaces;

import datamodel.ShortMessage;

public interface IInputOutput {
    void handle(Long userID, String message);
    ShortMessage readLine() throws InterruptedException;
}
