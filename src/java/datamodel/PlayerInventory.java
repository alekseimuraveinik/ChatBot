package datamodel;

import java.util.Objects;

public class PlayerInventory {
    private int gold, exp;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerInventory that = (PlayerInventory) o;
        return gold == that.gold &&
                exp == that.exp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gold, exp);
    }

    public String stringRepresentation(){
        return "Золото: " + gold + "\nОпыт: " + exp;
    }

    //ВСЕ ЧТО НАПИСАНО НИЖЕ ИСПОЛЬЗУЕТСЯ ДЛЯ СЕРИАЛИЗАЦИИ/ДЕСЕРИАЛИЗАЦИИ ОБЪЕКТА ПРИ РАБОТЕ С FIRESTORE

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
