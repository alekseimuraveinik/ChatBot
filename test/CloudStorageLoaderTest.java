import datamodel.Graph;
import datasource.CloudStorageLoader;
import db.Database;
import db.IDatabaseLoader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CloudStorageLoaderTest {
    private IDatabaseLoader db = new Database();
    private CloudStorageLoader storageLoader = new CloudStorageLoader(db);
    private Graph graph = storageLoader.getQuestionRoot();

    @Test
    void checkLoad(){
        assertNotNull(graph);
        assertNotNull(graph.getRoot());
    }

    @Test
    void checkChildLoad(){
        assertTrue(graph.getConnectedNodes(graph.getRoot()).size() > 0);
        assertNotNull(graph.getConnectedNodes(graph.getRoot()).get(0).getName());
        assertNotNull(graph.getConnectedNodes(graph.getRoot()).get(0).getQuestionContent());
    }
}
