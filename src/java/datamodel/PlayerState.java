package datamodel;

import logic.IChatLogic;
import logic.Player;

public class PlayerState {
    private GraphNode currentNode;
    private PlayerInventory playerInventory;
    private IChatLogic logic;
    private IChatLogic startLogic;

    public PlayerState(GraphNode startNode, PlayerInventory startInventory, IChatLogic defaultChatLogic){
        currentNode = startNode;
        playerInventory = startInventory;
        logic = defaultChatLogic;
        startLogic = defaultChatLogic;
    }

    public PlayerState(){
    }

    public IChatLogic getStartLogic() { return startLogic; }

    public GraphNode getCurrentNode() { return currentNode; }

    public PlayerInventory getPlayerInventory() { return playerInventory; }

    public IChatLogic getLogic() { return logic; }

    public void setCurrentNode(GraphNode node) { currentNode = node; }

    public void setPlayerInventory(PlayerInventory inventory) { playerInventory = inventory; }

    public void setLogic(IChatLogic logic) { this.logic = logic; }
}
