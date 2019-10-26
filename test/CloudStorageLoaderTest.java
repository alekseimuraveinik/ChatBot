import datamodel.Node;
import datasource.CloudStorageLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CloudStorageLoaderTest {
    @Test
    void checkRootQuestion(){
        Node root = new CloudStorageLoader().getQuestionRoot();

        assertEquals(root.getQuestionContent(), "Ты появился в волшебном мире, введи \"да\" чтобы начать игру");
    }

    @Test
    void checkYesVariantQuestion(){
        Node root = new CloudStorageLoader().getQuestionRoot();

        Node child = root.getChildByAnswer("да");

        assertEquals(child.getQuestionContent(), "Ты за альянс или орду?");
    }

    @Test
    void checkNonsenseVariantQuestion(){
        Node root = new CloudStorageLoader().getQuestionRoot();

        Node child = root.getChildByAnswer("ну наверное да");

        assertNull(child);
    }

    @Test
    void checkTerminatingQuestionChildNullness(){
        Node root = new CloudStorageLoader().getQuestionRoot();

        Node child = root.getChildByAnswer("да")
                .getChildByAnswer("альянс")
                .getChildByAnswer("печально");

        assertNull(child);
    }

    @Test
    void checkNotTerminatingQuestionChildNotNullness(){
        Node root = new CloudStorageLoader().getQuestionRoot();

        Node child = root.getChildByAnswer("да")
                .getChildByAnswer("орда")
                .getChildByAnswer("волшебник");

        assertNotNull(child);
    }

    @Test
    void checkHordeWizardQuestion(){
        Node root = new CloudStorageLoader().getQuestionRoot();

        Node child = root.getChildByAnswer("да")
                .getChildByAnswer("орда")
                .getChildByAnswer("волшебник");

        assertEquals(child.getQuestionContent(), "Ты появился в городе, куда пойдешь - на аукцион или на арену");
    }
}
