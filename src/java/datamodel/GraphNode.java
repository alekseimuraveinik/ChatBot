package datamodel;

import logic.PlayerModifier;

import java.util.Objects;

public class GraphNode {
    private String name;
    private String questionContent;
    private boolean deadNode;
    private PlayerModifier nodeModifier;

    public GraphNode(String name, String questionContent){
        this.name = name;
        this.questionContent = questionContent;
        deadNode = false;
        nodeModifier = new PlayerModifier();
    }

    public GraphNode(String name, String questionContent, PlayerModifier nodeModifier){
        this.name = name;
        this.questionContent = questionContent;
        deadNode = false;
        this.nodeModifier = nodeModifier;
    }

    public GraphNode(String name, String questionContent, boolean deadNode){
        this.name = name;
        this.questionContent = questionContent;
        this.deadNode = deadNode;
        nodeModifier = new PlayerModifier();
    }

    public String getName() { return name; }

    public String getQuestionContent() { return questionContent; }

    public boolean isDeadNode() {
        return deadNode;
    }

    public PlayerModifier getNodeModifier() { return nodeModifier; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNode graphNode = (GraphNode) o;
        return deadNode == graphNode.deadNode &&
                Objects.equals(name, graphNode.name) &&
                Objects.equals(questionContent, graphNode.questionContent) &&
                Objects.equals(nodeModifier.getClass(), graphNode.nodeModifier.getClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, questionContent, deadNode, nodeModifier.getClass());
    }

    //ВСЕ ЧТО НАПИСАНО НИЖЕ ИСПОЛЬЗУЕТСЯ ДЛЯ СЕРИАЛИЗАЦИИ/ДЕСЕРИАЛИЗАЦИИ ОБЪЕКТА ПРИ РАБОТЕ С FIRESTORE

    public GraphNode(){}

    public void setName(String name) {
        this.name = name;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public void setDeadNode(boolean deadNode) {
        this.deadNode = deadNode;
    }

    public void setNodeModifier(PlayerModifier nodeModifier) {
        this.nodeModifier = nodeModifier;
    }
}
