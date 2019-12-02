package datamodel;

import logic.DefaultPlayerModifier;
import logic.IPlayerModifier;

import java.util.Objects;

public class GraphNode {
    private String name;
    private String questionContent;
    private boolean deadNode;
    private IPlayerModifier nodeModifier;

    public GraphNode(String name, String questionContent){
        this.name = name;
        this.questionContent = questionContent;
        deadNode = false;
        nodeModifier = new DefaultPlayerModifier();
    }

    public GraphNode(String name, String questionContent, IPlayerModifier nodeModifier){
        this.name = name;
        this.questionContent = questionContent;
        deadNode = false;
        this.nodeModifier = nodeModifier;
    }

    public GraphNode(String name, String questionContent, boolean deadNode){
        this.name = name;
        this.questionContent = questionContent;
        this.deadNode = deadNode;
        nodeModifier = new DefaultPlayerModifier();
    }

    public String getName() { return name; }

    public String getQuestionContent() { return questionContent; }

    public boolean isDeadNode() {
        return deadNode;
    }

    public IPlayerModifier getNodeModifier() { return nodeModifier; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNode graphNode = (GraphNode) o;
        return deadNode == graphNode.deadNode &&
                Objects.equals(name, graphNode.name) &&
                Objects.equals(questionContent, graphNode.questionContent) &&
                Objects.equals(nodeModifier, graphNode.nodeModifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, questionContent, deadNode, nodeModifier);
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

    public void setNodeModifier(IPlayerModifier nodeModifier) {
        this.nodeModifier = nodeModifier;
    }
}
