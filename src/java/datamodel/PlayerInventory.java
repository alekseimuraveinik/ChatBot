package datamodel;

import logic.Player;

public class PlayerInventory {
    public int gold;
    public int exp;

    public PlayerInventory(){
        gold = 0;
        exp = 0;
    }

    public PlayerInventory(int gold, int exp){
        this.gold = gold;
        this.exp = exp;
    }

    public void AddOtherInventory(PlayerInventory inv){
        this.gold += inv.gold;
        this.exp += inv.exp;
    }

    public String ToString(){
        return "Золото: " + gold + "\nОпыт: " + exp;
    }
}
