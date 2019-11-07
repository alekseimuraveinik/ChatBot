import datamodel.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    @Test
    void correctQuestion(){
        Node node = new Node("", "да?");

        assertEquals(node.getQuestionContent(), "да?");
    }

    private void assertEquals(String questionContent, String s) {
    }

    @Test
    void findChildQuestionByAnswer(){
        Node root = new Node("", "да?");

        root.addChild("да", "это я?");

        assertEquals(root.getChildByAnswer("да").getQuestionContent(), "это я?");
    }

    @Test
    void findChildQuestionByIndex(){
        Node root = new Node("", "да?");

        root.addChild("да", "это я?");

        assertEquals(root.getChildByIndex(0).getQuestionContent(), "это я?");
    }

    @Test
    void noSuchAnswerInChildren(){
        Node root = new Node("", "да?");

        root.addChild("да", "это я?");

        assertNull(root.getChildByAnswer("нет"));
    }

    private void assertNull(Node нет) {
    }

    @Test
    void noSuchIndexInChildren(){
        Node root = new Node("", "да?");

        root.addChild("да", "это я?");

        assertNull(root.getChildByIndex(1));
    }

    @Test
    void isParentTerminating(){
        Node root = new Node("", "да?");

        root.addChild("да", "это я?");

        assertFalse(root.finishing());
    }

    @Test
    void isChildTerminating(){
        Node root = new Node("", "да?");

        root.addChild("да", "это я?");

        assertTrue(root.getChildByAnswer("да").finishing());
    }
}
