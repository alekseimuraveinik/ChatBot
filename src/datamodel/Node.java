package datamodel;

import java.util.ArrayList;

public class Node {
    private String questionContent;
    private String answerHolder;
    private boolean isTerminating;
    private ArrayList<Node> childs;
    public Node parent;

    public Node(String answer, String question){
        questionContent = question;
        answerHolder = answer;
        this.isTerminating = false;
        childs = new ArrayList<>();
        parent = null;
    }

    public Node( String answer, String question, boolean isTerminating){
        questionContent = question;
        answerHolder = answer;
        this.isTerminating = isTerminating;
        childs = new ArrayList<>();
        parent = null;
    }

    public Node AddChild(String answer, String quest){
        Node node = new Node(answer, quest, false);
        childs.add(node);
        node.parent = this;
        return node;
    }

    public Node AddChild(String answer, String quest, boolean isTerminating){
        Node node = new Node(answer, quest, isTerminating);
        childs.add(node);
        node.parent = this;
        return node;
    }

    public Node AddChild(Node child){
        child.parent = this;
        childs.add(child);
        return child;
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

    public boolean isTerminating(){
        return isTerminating;
    }
}
