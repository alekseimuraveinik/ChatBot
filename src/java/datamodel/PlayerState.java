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

    public PlayerState(){
    }

    public GraphNode getCurrentNode() { return currentNode; }

    public PlayerInventory getPlayerInventory() { return playerInventory; }

    public IMessageLogic getMessageLogic() { return messageLogic; }

    public void setCurrentNode(GraphNode node) { currentNode = node; }

    public void setPlayerInventory(PlayerInventory inventory) { playerInventory = inventory; }

    public void setMessageLogic(IMessageLogic logic) { this.messageLogic = logic; }

}
