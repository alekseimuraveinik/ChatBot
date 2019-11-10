package datamodel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphNode {
    private ArrayList<GraphNode> incidentNodes;
    private String name;
    private String questionContent;
    private Boolean isDeadNode;

    public GraphNode()
    {
        incidentNodes = new ArrayList<>();
        this.isDeadNode = false;
    }

    public GraphNode(String name, String questionContent)
    {
        incidentNodes = new ArrayList<>();
        this.isDeadNode = false;
        this.name = name;
        this.questionContent = questionContent;
    }

    public GraphNode(String name, String questionContent, Boolean isDeadNode)
    {
        incidentNodes = new ArrayList<>();
        this.name = name;
        this.questionContent = questionContent;
        this.isDeadNode = isDeadNode;
    }


    public void addIncidentNode(String name, String questionContent)
    {
        GraphNode node = new GraphNode(name, questionContent);
        incidentNodes.add(node);
        node.incidentNodes.add(this);
    }

    public void addIncidentNode(GraphNode node)
    {
        incidentNodes.add(node);
        node.incidentNodes.add(this);
    }

    public void addOneWayIncidentNode(GraphNode node)
    {
        incidentNodes.add(node);
    }

    public String getQuestionContent(){ return questionContent; }

    public GraphNode getChildByAnswer(String answer){
        for(GraphNode child : incidentNodes){
            if(child.name.toLowerCase().equals(answer))
                return child;
        }
        return null;
    }

    public GraphNode getChildByIndex(int index){
        try {
            return incidentNodes.get(index);
        } catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    public boolean finishing(){
        return isDeadNode;
    }

    public boolean isEnd() { return incidentNodes.size() <= 1; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getFormattedContentAndNextNodes(){
        StringBuilder mesContent = new StringBuilder(questionContent);
        mesContent.append("\nВыберете куда пойти дальше дальше:");
        for (GraphNode node : incidentNodes){
            mesContent.append("\n -  ");
            mesContent.append(node.getName());
        }
        return mesContent.toString();
    }

    public ArrayList<GraphNode> getIncidentNodes() {
        return incidentNodes;
    }

    public void setIncidentNodes(ArrayList<GraphNode> incidentNodes) {
        this.incidentNodes = incidentNodes;
    }
}
