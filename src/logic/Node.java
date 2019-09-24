package logic;

import java.util.ArrayList;

public class Node {
    public String quest;
    public String answ;
    public ArrayList<Node> childs;


    public Node( String answ, String quest){
        this.quest = quest;
        this.answ = answ;
        childs = new ArrayList<Node>();
    }

    public Node AddChild(String answ, String quest){
        Node node = new Node(answ, quest);
        childs.add(node);
        return node;
    }
}
