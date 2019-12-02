package logic;

import datamodel.PlayerInventory;

public class DefaultPlayerModifier implements IPlayerModifier {
    private PlayerInventory addingInventory;
    private IChatLogic newLogic;

    public DefaultPlayerModifier(PlayerInventory addingInventory, IChatLogic newLogic){
        this.addingInventory = addingInventory;
        this.newLogic = newLogic;
    }

    public DefaultPlayerModifier(PlayerInventory addingInventory){
        this.addingInventory = addingInventory;
        this.newLogic = null;
    }

    public DefaultPlayerModifier(){
        this.addingInventory = new PlayerInventory();
        this.newLogic = null;
    }

    @Override
    public void modify(IPlayer player) {
        if (newLogic != null)
            player.getPlayerState().setLogic(newLogic);

        player.getPlayerState().getPlayerInventory().AddOtherInventory(addingInventory);
    }
}
