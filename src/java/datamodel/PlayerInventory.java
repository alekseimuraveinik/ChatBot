package datamodel;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerInventory {
    private int gold, exp, reputation;
    public final ArrayList<Item> items;

    public PlayerInventory(){
        gold = 0;
        exp = 0;
        reputation = 0;
        items = new ArrayList<>();
    }

    public PlayerInventory(int gold, int exp, int reputation){
        this.gold = gold;
        this.exp = exp;
        this.reputation = reputation;
        items = new ArrayList<>();
    }

    public void AddOtherInventory(PlayerInventory inv){
        this.gold += inv.gold;
        this.exp += inv.exp;
        this.reputation += inv.reputation;
        for (Item item : inv.items){
            items.add(item);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerInventory that = (PlayerInventory) o;

        if (that.items.size() != items.size())
            return false;

        for (Item item : items){
            if (!that.items.contains(item))
                return false;
        }

        return gold == that.gold &&
                exp == that.exp &&
                reputation == that.reputation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gold, exp, reputation, items);
    }

    public String stringRepresentation(){
        StringBuilder str = new StringBuilder();

        str.append("Золото: ");
        str.append(gold);

        str.append("\nОпыт: ");
        str.append(exp);

        str.append("\nРепутация: ");
        str.append(reputation);

        str.append("\nВаши предметы:");
        for (Item item : items){
            str.append("\n - ");
            str.append(item.name);
        }

        return str.toString();
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

    public int getReputation() { return reputation; }

    public void setReputation(int reputation) { this.reputation = reputation; }
}
