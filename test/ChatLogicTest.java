import datasource.QuestionLoader;
import interfaces.IChatLogic;
import logic.ChatLogic;
import org.junit.jupiter.api.Test;
import auxiliary.MessageHolder;

import static org.junit.jupiter.api.Assertions.*;

class ChatLogicTest {
    private static final String filename = "newformat.txt";

    @Test
    void correctGameStartingMessage(){
        MessageHolder holder = new MessageHolder();
        IChatLogic logic = new ChatLogic(new QuestionLoader(filename), 0L);
        logic.subscribe(holder);

        String logicMessage = holder.getMessage();

        assertEquals(logicMessage, "Ты появился в волшебном мире, введи \"да\" чтобы начать игру");
    }

    @Test
    void allianceOrHordeQuestion(){
        MessageHolder holder = new MessageHolder();
        IChatLogic logic = new ChatLogic(new QuestionLoader(filename), 0L);
        logic.subscribe(holder);

        logic.processMessage("да");
        String logicMessage = holder.getMessage();

        assertEquals(logicMessage, "Ты за альянс или орду?");
    }

    @Test
    void allianceVariantChosen(){
        MessageHolder holder = new MessageHolder();
        IChatLogic logic = new ChatLogic(new QuestionLoader(filename), 0L);
        logic.subscribe(holder);

        logic.processMessage("да");
        logic.processMessage("альянс");
        String logicMessage = holder.getMessage();

        assertEquals(logicMessage, "ты проиграл~");
    }

    @Test
    void hordeVariantChosen(){
        MessageHolder holder = new MessageHolder();
        IChatLogic logic = new ChatLogic(new QuestionLoader(filename), 0L);
        logic.subscribe(holder);

        logic.processMessage("да");
        logic.processMessage("орда");
        String logicMessage = holder.getMessage();

        assertEquals(logicMessage, "Ты волшебник, воин, друид или вор");
    }

    @Test
    void noSuchVariant(){
        MessageHolder holder = new MessageHolder();
        IChatLogic logic = new ChatLogic(new QuestionLoader(filename), 0L);
        logic.subscribe(holder);

        logic.processMessage("не знаю");

        String logicMessage = holder.getMessage();

        assertEquals(logicMessage, "Такого варианта не предусмотрено");
    }
}
