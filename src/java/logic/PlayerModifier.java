package logic;

import datamodel.PlayerInventory;

import java.util.Objects;

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
        player.getState().getPlayerInventory().AddOtherInventory(addingInventory);

        if (newLogic != null) {
            player.getState().subscribe(newLogic);
            player.handle(newLogic.getNewPlayerMessage(player));
        }
    }

    public IChatLogic newLogic() { return  newLogic; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerModifier that = (PlayerModifier) o;
        return Objects.equals(addingInventory, that.addingInventory) &&
                Objects.equals(newLogic, that.newLogic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addingInventory, newLogic);
    }

    //ВСЕ ЧТО НАПИСАНО НИЖЕ ИСПОЛЬЗУЕТСЯ ДЛЯ СЕРИАЛИЗАЦИИ/ДЕСЕРИАЛИЗАЦИИ ОБЪЕКТА ПРИ РАБОТЕ С FIRESTORE


    public PlayerInventory getAddingInventory() {
        return addingInventory;
    }

    public void setAddingInventory(PlayerInventory addingInventory) {
        this.addingInventory = addingInventory;
    }
}
