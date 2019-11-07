package datamodel;

import java.util.ArrayList;
import java.util.List;

public class Node {
    protected String questionContent;
    protected String answerHolder;
    protected List<Node> children;

    public Node(){
        children = new ArrayList<>();
    }

    public Node(String answer, String question){
        questionContent = question;
        answerHolder = answer;
    }

    public void addChild(String answer, String quest){
        Node node = new Node(answer, quest);
        children.add(node);
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public Node getChildByAnswer(String answer){
        for(Node child : children){
            if(child.answerHolder.equals(answer))
                return child;
        }
        return null;
    }

    public Node getChildByIndex(int index){
        try {
            return children.get(index);
        } catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    public boolean isTerminating(){
        return children.size() == 0;
    }
}
