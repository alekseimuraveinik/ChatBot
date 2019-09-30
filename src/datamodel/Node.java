package datamodel;

import java.util.ArrayList;

public class Node {
    private String questionContent;
    private String answerHolder;
    private ArrayList<Node> childs;


    public Node( String answer, String question){
        questionContent = question;
        answerHolder = answer;
        childs = new ArrayList<>();
    }

    public Node AddChild(String answer, String quest){
        Node node = new Node(answer, quest);
        childs.add(node);
        return node;
    }
}
