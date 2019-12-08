package logic;

import datamodel.PlayerInventory;

public class PlayerModifier {
    private PlayerInventory addingInventory;
    private IMessageLogic newLogic;

    public PlayerModifier(PlayerInventory addingInventory, IMessageLogic newLogic){
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
            player.getPlayerState().setMessageLogic(newLogic);
            player.handle(newLogic.getHelloMessage(player));
        }
    }

    public IMessageLogic getNewLogic() { return  newLogic; }
}
