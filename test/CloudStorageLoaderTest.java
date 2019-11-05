import datamodel.Node;
import datasource.CloudStorageLoader;
import db.Database;
import interfaces.IDatabaseLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CloudStorageLoaderTest {
    private IDatabaseLoader db = new Database("firebase_api_key.json");
    private CloudStorageLoader storageLoader = new CloudStorageLoader(db);
    private Node root = storageLoader.getQuestionRoot();

    @Test
    void checkRootQuestion(){
        assertEquals(root.getQuestionContent(), "Ты появился в волшебном мире, введи \"да\" чтобы начать игру");
    }

    @Test
    void checkYesVariantQuestion(){
        Node child = root.getChildByAnswer("да");

        assertEquals(child.getQuestionContent(), "Ты за альянс или орду?");
    }

    @Test
    void checkNonsenseVariantQuestion(){
        Node child = root.getChildByAnswer("ну наверное да");

        assertNull(child);
    }

    @Test
    void checkTerminatingQuestionChildNullness(){
        Node child = root.getChildByAnswer("да")
                .getChildByAnswer("альянс")
                .getChildByAnswer("печально");

        assertNull(child);
    }

    @Test
    void checkNotTerminatingQuestionChildNotNullness(){
        Node child = root.getChildByAnswer("да")
                .getChildByAnswer("орда")
                .getChildByAnswer("волшебник");

        assertNotNull(child);
    }

    @Test
    void checkHordeWizardQuestion(){
        Node child = root.getChildByAnswer("да")
                .getChildByAnswer("орда")
                .getChildByAnswer("волшебник");

        assertEquals(child.getQuestionContent(), "Ты появился в городе, куда пойдешь - на аукцион или на арену");
    }
}
