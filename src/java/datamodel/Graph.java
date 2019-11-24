package datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private HashMap<GraphNode, ArrayList<GraphNode>> connections;
    public GraphNode currentNode;
    private GraphNode root;
    private static final String NEXT_MESSAGE = "\nВыберете куда пойти дальше дальше:";

    public Graph()
    {
        connections = new HashMap<>();
    }

    public Graph(String name, String questionContent)
    {
        connections = new HashMap<>();
        currentNode = new GraphNode(name, questionContent);
        root = currentNode;
    }

    public Graph(String name, String questionContent, boolean isDeadNode)
    {
        connections = new HashMap<>();
        currentNode = new GraphNode(name, questionContent, isDeadNode);
        root = currentNode;
    }

    public void resetToRoot(){
        currentNode = root;
    }

    public void addIncidentNode(String name, String questionContent)
    {
        GraphNode node = new GraphNode(name, questionContent);
        addIncidentNode(node);
    }

    public void addIncidentNode(GraphNode node)
    {
        connectNodes(currentNode, node);
    }

    public void addOneWayIncidentNode(GraphNode node)
    {
        oneWayConnectNodes(currentNode, node);
    }

    public void oneWayConnectNodes(GraphNode nodeFrom, GraphNode nodeTo){
        if (!connections.containsKey(nodeFrom))
            connections.put(nodeFrom, new ArrayList<>());

        connections.get(nodeFrom).add(nodeTo);
    }

    public void connectNodes(GraphNode nodeOne, GraphNode nodeTwo){
        oneWayConnectNodes(nodeOne, nodeTwo);
        oneWayConnectNodes(nodeTwo, nodeOne);
    }

    public String getQuestionContent(){ return currentNode.getQuestionContent(); }

    public GraphNode getChildByAnswer(String answer){
        if (connections.get(currentNode) == null)
            return null;

        for(GraphNode child : connections.get(currentNode)){
            if(child.getName().toLowerCase().equals(answer))
                return child;
        }
        return null;
    }

    public boolean finishing(){
        return currentNode.getIsDead();
    }

    public boolean isEnd() { return connections.get(currentNode).size() <= 1; }

    public String getName() { return currentNode.getName(); }

    public String getFormattedContentAndNextNodes(){
        if (connections.get(currentNode) == null)
            return currentNode.getQuestionContent();

        StringBuilder mesContent = new StringBuilder(currentNode.getQuestionContent());
        mesContent.append(NEXT_MESSAGE);
        for (GraphNode node : connections.get(currentNode)){
            mesContent.append("\n -  ");
            mesContent.append(node.getName());
        }
        return mesContent.toString();
    }

    public ArrayList<GraphNode> getIncidentNodes() {
        return connections.get(currentNode);
    }

    public void setIncidentNodes(ArrayList<GraphNode> incidentNodes) {
        connections.put(currentNode, incidentNodes);
    }
}
