package datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private ArrayList<GraphNode> graphNodes;
    private ArrayList<ArrayList<Integer>> connections;
    public GraphNode currentNode;
    private GraphNode root;
    private static final String NEXT_MESSAGE = "\nВыберете куда пойти дальше дальше:";

    public Graph()
    {
        graphNodes = new ArrayList<>();
        connections = new ArrayList<>();
    }

    public Graph(String name, String questionContent)
    {
        graphNodes = new ArrayList<>();
        connections = new ArrayList<>();
        currentNode = new GraphNode(name, questionContent);
        root = currentNode;
    }

    public Graph(String name, String questionContent, boolean isDeadNode)
    {
        graphNodes = new ArrayList<>();
        connections = new ArrayList<>();
        currentNode = new GraphNode(name, questionContent, isDeadNode);
        root = currentNode;
    }

    public void resetToRoot(){
        currentNode = root;
    }

    public void addIncidentNode(GraphNode node)
    {
        connectNodes(currentNode, node);
    }

    public void oneWayConnectNodes(GraphNode nodeFrom, GraphNode nodeTo){
        if (!graphNodes.contains(nodeFrom)){
            graphNodes.add(nodeFrom);
            connections.add(new ArrayList<>());
        }
        if (!graphNodes.contains(nodeTo)) {
            graphNodes.add(nodeTo);
            connections.add(new ArrayList<>());
        }

        connections.get(graphNodes.indexOf(nodeFrom)).add(graphNodes.indexOf(nodeTo));
    }

    public void connectNodes(GraphNode nodeOne, GraphNode nodeTwo){
        oneWayConnectNodes(nodeOne, nodeTwo);
        oneWayConnectNodes(nodeTwo, nodeOne);
    }

    public String getQuestionContent(){ return currentNode.getQuestionContent(); }

    public GraphNode getChildByAnswer(String answer){
        if (!graphNodes.contains(currentNode))
            return null;

        for (Integer i : connections.get(graphNodes.indexOf(currentNode))){
            if (graphNodes.get(i).getName().toLowerCase().equals(answer))
                return graphNodes.get(i);
        }

        return null;
    }

    public boolean finishing(){
        return currentNode.getIsDead();
    }

    public String getName() { return currentNode.getName(); }

    public String getFormattedContentAndNextNodes(){
        if (!graphNodes.contains(currentNode))
            return currentNode.getQuestionContent();

        StringBuilder mesContent = new StringBuilder(currentNode.getQuestionContent());
        mesContent.append(NEXT_MESSAGE);
        for (Integer i : connections.get(graphNodes.indexOf(currentNode))){
            mesContent.append("\n -  ");
            mesContent.append(graphNodes.get(i).getName());
        }
        return mesContent.toString();
    }
}
