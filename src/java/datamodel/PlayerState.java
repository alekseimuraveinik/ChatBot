package datamodel;

import logic.IMessageLogic;

public class PlayerState {
    private IMessageLogic messageLogic;
    private GraphNode currentNode;
    private PlayerInventory playerInventory;

    public PlayerState(GraphNode startNode, PlayerInventory startInventory){
        currentNode = startNode;
        playerInventory = startInventory;
    }

    public IMessageLogic currentLogic() { return messageLogic; }

    public void switchLogic(IMessageLogic logic) { this.messageLogic = logic; }

    public PlayerState(){

    }

    public GraphNode getCurrentNode() { return currentNode; }

    public PlayerInventory getPlayerInventory() { return playerInventory; }

    public void setCurrentNode(GraphNode node) { currentNode = node; }

    public void setPlayerInventory(PlayerInventory inventory) { playerInventory = inventory; }
}
