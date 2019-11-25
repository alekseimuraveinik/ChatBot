import db.Database;
import db.IDatabaseLoader;
import logic.Callboard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CallboardTest {
    private IDatabaseLoader db = new Database("firebase_api_key.json");

    @Test
    public void LoadTest(){
        Callboard cb = new Callboard(db);
        assertNotNull(cb);
        assertNotNull(cb.getCallboardRecords());
    }
}
