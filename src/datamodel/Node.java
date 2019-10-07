package datamodel;

import java.util.ArrayList;

public class Node {
    private String questionContent;
    private String answerHolder;
    private boolean isTerminating = true;
    private ArrayList<Node> children = new ArrayList<>();

    public Node(String answer, String question){
        questionContent = question;
        answerHolder = answer;
    }

    public void addChild(String answer, String quest){
        Node node = new Node(answer, quest);
        children.add(node);
        isTerminating = false;
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

    public String getQuestionContent() {
        return questionContent;
    }

    public boolean isTerminating(){
        return isTerminating;
    }
}
