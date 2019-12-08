package datamodel;


import java.util.ArrayList;

public class Graph {
    private ArrayList<GraphNode> graphNodes;
    private ArrayList<IntPair> connections;
    private GraphNode root;
    private static final String NEXT_MESSAGE = "\nВыберете куда пойти дальше дальше:";

    public GraphNode getRoot() {
        return root;
    }

    public Graph(String name, String questionContent)
    {
        graphNodes = new ArrayList<>();
        connections = new ArrayList<>();
        root = new GraphNode(name, questionContent);
    }


    public void addIncidentNode(GraphNode node)
    {
        connectNodes(root, node);
    }

    public void oneWayConnectNodes(GraphNode nodeFrom, GraphNode nodeTo){
        if (!graphNodes.contains(nodeFrom)){
            graphNodes.add(nodeFrom);
        }
        if (!graphNodes.contains(nodeTo)) {
            graphNodes.add(nodeTo);
        }

        connections.add(new IntPair(graphNodes.indexOf(nodeFrom), graphNodes.indexOf(nodeTo)));
    }

    public void connectNodes(GraphNode nodeOne, GraphNode nodeTwo){
        oneWayConnectNodes(nodeOne, nodeTwo);
        oneWayConnectNodes(nodeTwo, nodeOne);
    }

    public ArrayList<GraphNode> getConnectedNodes(GraphNode graphNode){
        ArrayList<GraphNode> result = new ArrayList<>();
        int currentNodeIndex = graphNodes.indexOf(graphNode);
        for(IntPair edge : connections){
            if(edge.getKey() == currentNodeIndex)
                result.add(graphNodes.get(edge.getValue()));
        }
        return result;
    }

    public GraphNode getChildByAnswer(GraphNode Node, String answer){
        if (!graphNodes.contains(Node))
            return null;

        for (GraphNode graphNode : getConnectedNodes(Node)){
            if (graphNode.getName().toLowerCase().equals(answer))
                return graphNode;
        }

        return null;
    }

    public String formattedContentAndNextNodes(GraphNode currentNode){
        ArrayList connections = getConnectedNodes(currentNode);
        if (connections.size() == 0)
            return currentNode.getQuestionContent();

        StringBuilder mesContent = new StringBuilder(currentNode.getQuestionContent());
        mesContent.append(NEXT_MESSAGE);
        for (GraphNode graphNode : getConnectedNodes(currentNode)){
            mesContent.append("\n -  ");
            mesContent.append(graphNode.getName());
        }
        return mesContent.toString();
    }

    //ВСЕ ЧТО НАПИСАНО НИЖЕ ИСПОЛЬЗУЕТСЯ ДЛЯ СЕРИАЛИЗАЦИИ/ДЕСЕРИАЛИЗАЦИИ ОБЪЕКТА ПРИ РАБОТЕ С FIRESTORE

    public Graph()
    {

    }

    public ArrayList<GraphNode> getGraphNodes() {
        return graphNodes;
    }

    public void setGraphNodes(ArrayList<GraphNode> graphNodes) {
        this.graphNodes = graphNodes;
    }

    public ArrayList<IntPair> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<IntPair> connections) {
        this.connections = connections;
    }

    public void setRoot(GraphNode root) {
        this.root = root;
    }
}
