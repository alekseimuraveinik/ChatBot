package logic;

import datamodel.PlayerInventory;

public class PlayerModifier {
    private PlayerInventory addingInventory;
    private boolean cards = false;

    public PlayerModifier(PlayerInventory addingInventory){
        this.addingInventory = addingInventory;
    }

    public PlayerModifier(){
        this.addingInventory = new PlayerInventory();
    }

    public boolean modify(IPlayer player) {
        player.getState().getPlayerInventory().AddOtherInventory(addingInventory);
        return cards;
    }

    public PlayerInventory getAddingInventory() {
        return addingInventory;
    }

    public void setAddingInventory(PlayerInventory addingInventory) {
        this.addingInventory = addingInventory;
    }

    public boolean isCards() {
        return cards;
    }

    public void setCards(boolean cards) {
        this.cards = cards;
    }
}
