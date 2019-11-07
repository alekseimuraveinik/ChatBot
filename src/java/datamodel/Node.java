package datamodel;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String questionContent;
    private String answerHolder;
    private List<Node> children;

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

    public boolean finishing(){
        return children.size() == 0;
    }

    public String getAnswerHolder() {
        return answerHolder;
    }

    public void setAnswerHolder(String answerHolder) {
        this.answerHolder = answerHolder;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}
