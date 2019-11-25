package datamodel;


import java.util.ArrayList;

public class Graph {
    private ArrayList<GraphNode> graphNodes;
    private ArrayList<IntPair> connections;
    public GraphNode currentNode;
    private GraphNode root;
    private static final String NEXT_MESSAGE = "\nВыберете куда пойти дальше дальше:";

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

    public GraphNode getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(GraphNode currentNode) {
        this.currentNode = currentNode;
        if(graphNodes != null)
            graphNodes.add(currentNode);
    }

    public GraphNode getRoot() {
        return root;
    }

    public void setRoot(GraphNode root) {
        this.root = root;
    }

    public Graph()
    {

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

    private ArrayList<GraphNode> getConnectedNodes(GraphNode graphNode){
        ArrayList<GraphNode> result = new ArrayList<>();
        int currentNodeIndex = graphNodes.indexOf(graphNode);
        for(IntPair edge : connections){
            if(edge.getKey() == currentNodeIndex)
                result.add(graphNodes.get(edge.getValue()));
        }
        return result;
    }

    public String currentNodeQuestionContent(){ return currentNode.getQuestionContent(); }

    public GraphNode getChildByAnswer(String answer){
        if (!graphNodes.contains(currentNode))
            return null;

        for (GraphNode graphNode : getConnectedNodes(currentNode)){
            if (graphNode.getName().toLowerCase().equals(answer))
                return graphNode;
        }

        return null;
    }

    public boolean finishing(){
        return currentNode.isDeadNode();
    }

    public String formattedContentAndNextNodes(){
        if (!graphNodes.contains(currentNode))
            return currentNode.getQuestionContent();

        StringBuilder mesContent = new StringBuilder(currentNode.getQuestionContent());
        mesContent.append(NEXT_MESSAGE);
        for (GraphNode graphNode : getConnectedNodes(currentNode)){
            mesContent.append("\n -  ");
            mesContent.append(graphNode.getName());
        }
        return mesContent.toString();
    }
}
