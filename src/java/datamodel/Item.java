package datamodel;

import java.util.Objects;

public class Item {
    public final String name;
    public final int itemId;

    public Item(String name, int id){
        this.name = name;
        itemId = id;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item)o;

        return name.equals(item.name) &&
                itemId == item.itemId;
    }

    public int hashCode() {
        return Objects.hash(name, itemId);
    }

}
