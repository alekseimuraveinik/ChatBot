package datamodel;

import java.util.ArrayList;

public class Node {
    private String questionContent;
    private String answerHolder;
    private ArrayList<Node> children = new ArrayList<>();

    public Node(){

    }

    public Node(String answer, String question){
        questionContent = question;
        answerHolder = answer;
    }

    public void addChild(String answer, String quest){
        Node node = new Node(answer, quest);
        children.add(node);
    }

    public String getAnswerHolder() {
        return answerHolder;
    }

    public void setAnswerHolder(String answerHolder) {
        this.answerHolder = answerHolder;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
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
