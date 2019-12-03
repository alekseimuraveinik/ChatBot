package logic;

import datamodel.PlayerInventory;

public class PlayerModifier {
    private PlayerInventory addingInventory;
    private IChatLogic newLogic;

    public PlayerModifier(PlayerInventory addingInventory, IChatLogic newLogic){
        this.addingInventory = addingInventory;
        this.newLogic = newLogic;
    }

    public PlayerModifier(PlayerInventory addingInventory){
        this.addingInventory = addingInventory;
        this.newLogic = null;
    }

    public PlayerModifier(){
        this.addingInventory = new PlayerInventory();
        this.newLogic = null;
    }

    public void modify(IPlayer player) {
        player.getPlayerState().getPlayerInventory().AddOtherInventory(addingInventory);

        if (newLogic != null) {
            player.getPlayerState().setLogic(newLogic);
            player.handle(newLogic.getNewPlayerMessage(player));
        }
    }

    public IChatLogic getNewLogic() { return  newLogic; }
}
