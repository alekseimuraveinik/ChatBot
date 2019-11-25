import datamodel.Graph;
import datamodel.GraphNode;
import datamodel.PlayerInventory;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    @Test
    public void checkNormalStorage(){
        Graph graph = new Graph("1", "1");
        GraphNode n2 = new GraphNode("2", "2");
        graph.connectNodes(graph.getRoot(), n2);
        assertEquals(graph.getChildByAnswer(graph.getRoot(), "2"), n2);
        assertEquals(graph.getChildByAnswer(n2, "1"), graph.getRoot());
        assertNull(graph.getChildByAnswer(graph.getRoot(), "3"));
    }

    @Test
    public void checkOneWay(){
        Graph graph = new Graph("1", "1");
        GraphNode n2 = new GraphNode("2", "2");

        graph.oneWayConnectNodes(graph.getRoot(), n2);
        assertEquals(graph.getConnectedNodes(graph.getRoot()).size(), 1);
        assertEquals(graph.getConnectedNodes(n2).size(), 0);

        graph.oneWayConnectNodes(n2, graph.getRoot());
        assertEquals(graph.getConnectedNodes(graph.getRoot()).size(), 1);
        assertEquals(graph.getConnectedNodes(n2).size(), 1);
    }

    @Test
    public void testGraphNodeEquals(){
        GraphNode g1 = new GraphNode("1", "2", new PlayerInventory(10, 11));
        GraphNode g2 = new GraphNode("1", "2", new PlayerInventory(10, 11));

        assertEquals(g1, g2);
    }
}
