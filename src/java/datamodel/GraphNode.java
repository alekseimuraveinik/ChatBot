package datamodel;

public class GraphNode {
    private String name;
    private String questionContent;
    private boolean isDeadNode;
    private PlayerInventory nodePrize;

    public GraphNode(){
        isDeadNode = false;
    }

    public GraphNode(String name, String questionContent){
        this.name = name;
        this.questionContent = questionContent;
        isDeadNode = false;
        nodePrize = new PlayerInventory(0,0);
    }

    public GraphNode(String name, String questionContent, PlayerInventory prize){
        this.name = name;
        this.questionContent = questionContent;
        isDeadNode = false;
        this.nodePrize = prize;
    }

    public GraphNode(String name, String questionContent, boolean isDeadNode){
        this.name = name;
        this.questionContent = questionContent;
        this.isDeadNode = isDeadNode;
        nodePrize = new PlayerInventory(0,0);
    }

    public GraphNode(String name, String questionContent, boolean isDeadNode, PlayerInventory prize){
        this.name = name;
        this.questionContent = questionContent;
        this.isDeadNode = isDeadNode;
        this.nodePrize = prize;
    }

    public String getName() { return name; }

    public String getQuestionContent() { return questionContent; }

    public boolean getIsDead() { return isDeadNode; }

    public PlayerInventory getNodePrize() { return nodePrize; }

    public void setName(String name) {this.name = name;}

    public void setQuestionContent(String questionContent) { this.questionContent = questionContent; }

    public void setIsDeadNode(boolean isDeadNode) { this.isDeadNode = isDeadNode; }

    public void setNodePrize(PlayerInventory nodePrize) { this.nodePrize = nodePrize; }
}
