package datamodel;

import java.util.Objects;

public class GraphNode {
    private String name;
    private String questionContent;
    private boolean deadNode;
    private PlayerInventory nodePrize;

    public GraphNode(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public boolean isDeadNode() {
        return deadNode;
    }

    public void setDeadNode(boolean deadNode) {
        this.deadNode = deadNode;
    }

    public void setNodePrize(PlayerInventory nodePrize) {
        this.nodePrize = nodePrize;
    }

    public GraphNode(String name, String questionContent){
        this.name = name;
        this.questionContent = questionContent;
        deadNode = false;
        nodePrize = new PlayerInventory(0,0);
    }

    public GraphNode(String name, String questionContent, PlayerInventory prize){
        this.name = name;
        this.questionContent = questionContent;
        deadNode = false;
        this.nodePrize = prize;
    }

    public GraphNode(String name, String questionContent, boolean deadNode){
        this.name = name;
        this.questionContent = questionContent;
        this.deadNode = deadNode;
        nodePrize = new PlayerInventory(0,0);
    }

    public GraphNode(String name, String questionContent, boolean deadNode, PlayerInventory prize){
        this.name = name;
        this.questionContent = questionContent;
        this.deadNode = deadNode;
        this.nodePrize = prize;
    }

    public String getName() { return name; }

    public String getQuestionContent() { return questionContent; }

    public PlayerInventory getNodePrize() { return nodePrize; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNode graphNode = (GraphNode) o;
        return deadNode == graphNode.deadNode &&
                Objects.equals(name, graphNode.name) &&
                Objects.equals(questionContent, graphNode.questionContent) &&
                Objects.equals(nodePrize, graphNode.nodePrize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, questionContent, deadNode, nodePrize);
    }
}
