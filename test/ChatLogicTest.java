import auxiliary.QuestionLoader;
import datamodel.UserID;
import datasource.IQuestionGettable;
import logic.*;
import org.junit.jupiter.api.Test;
import auxiliary.MessageHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChatLogicTest {
    private static final String apiFilename = "firebase_api_key.json";

    @Test
    void correctGameStartingMessage(){
        Callboard callboard = mock(Callboard.class);
        MessageHolder holder = new MessageHolder();
        IQuestionGettable getter = mock(IQuestionGettable.class);
        when(getter.getQuestionRoot()).thenReturn(new QuestionLoader().getQuestionRoot());
        IChatLogic logic = new ChatLogic(getter, callboard);
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder);

        String logicMessage = holder.getMessage();

        assertEquals(logicMessage, "Ты появился в волшебном мире, введи \"да\" чтобы начать игру");
    }

    @Test
    void allianceOrHordeQuestion(){
        Callboard callboard = mock(Callboard.class);
        MessageHolder holder = new MessageHolder();
        IQuestionGettable getter = mock(IQuestionGettable.class);
        when(getter.getQuestionRoot()).thenReturn(new QuestionLoader().getQuestionRoot());
        IChatLogic logic = new ChatLogic(getter, callboard);
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder);

        player.processMessage("да");
        String logicMessage = holder.getMessage();

        assertEquals(logicMessage, "Ты за альянс или орду?");
    }

    @Test
    void allianceVariantChosen(){
        Callboard callboard = mock(Callboard.class);
        MessageHolder holder = new MessageHolder();
        IQuestionGettable getter = mock(IQuestionGettable.class);
        when(getter.getQuestionRoot()).thenReturn(new QuestionLoader().getQuestionRoot());
        IChatLogic logic = new ChatLogic(getter, callboard);
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder);

        player.processMessage("да");
        player.processMessage("альянс");
        String logicMessage = holder.getMessage();

        assertEquals(logicMessage, "ты проиграл~");
    }

    @Test
    void hordeVariantChosen(){
        Callboard callboard = mock(Callboard.class);
        MessageHolder holder = new MessageHolder();
        IQuestionGettable getter = mock(IQuestionGettable.class);
        when(getter.getQuestionRoot()).thenReturn(new QuestionLoader().getQuestionRoot());
        IChatLogic logic = new ChatLogic(getter, callboard);
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder);

        player.processMessage("да");
        player.processMessage("орда");
        String logicMessage = holder.getMessage();

        assertEquals(logicMessage, "Ты волшебник, воин, друид или вор");
    }

    @Test
    void noSuchVariant(){
        Callboard callboard = mock(Callboard.class);
        MessageHolder holder = new MessageHolder();
        IQuestionGettable getter = mock(IQuestionGettable.class);
        when(getter.getQuestionRoot()).thenReturn(new QuestionLoader().getQuestionRoot());
        IChatLogic logic = new ChatLogic(getter, callboard);
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder);

        player.processMessage("не знаю");

        String logicMessage = holder.getMessage();

        assertEquals(logicMessage, "Такого варианта не предусмотрено");
    }
}
