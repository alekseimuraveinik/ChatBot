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

    public Node getChildByAnswer(String answer){
        for(Node child : childs){
            if(child.answerHolder.equals(answer))
                return child;
        }
        return null;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public String getAnswerHolder() {
        return answerHolder;
    }
}
