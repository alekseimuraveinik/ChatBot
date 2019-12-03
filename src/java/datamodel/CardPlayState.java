package datamodel;

import java.util.ArrayList;

public class CardPlayState {
    public ArrayList<Integer> cards;
    public ArrayList<Integer> enemyCards;

    public CardPlayState(){
        cards = new ArrayList<>();
        enemyCards = new ArrayList<>();
    }
}
