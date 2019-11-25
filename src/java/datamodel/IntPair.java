package datamodel;

public class IntPair {
    private int key, value;

    public IntPair(int key, int value){
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }


    public int getValue() {
        return value;
    }

    //ВСЕ ЧТО НАПИСАНО НИЖЕ ИСПОЛЬЗУЕТСЯ ДЛЯ СЕРИАЛИЗАЦИИ/ДЕСЕРИАЛИЗАЦИИ ОБЪЕКТА ПРИ РАБОТЕ С FIRESTORE

    public IntPair(){}

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
