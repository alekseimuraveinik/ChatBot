import datamodel.UserID;
import datasource.IQuestionGettable;
import datasource.TestQuestionsLoader;
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
        when(getter.getQuestionRoot()).thenReturn(new TestQuestionsLoader().getQuestionRoot());
        IChatLogic logic = new ChatLogic(getter, callboard);
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder, true);

        String logicMessage = holder.getMessage();

        assertEquals("Добро пожаловать в текстовый РПГ мир\n" +
                "Вы находитесь на главной площади города:\n" +
                "Выберете куда пойти дальше дальше:\n" +
                " -  Таверна\n" +
                " -  Магазин\n" +
                " -  Воровать", logicMessage);
    }

    @Test
    void goToTavern(){
        Callboard callboard = mock(Callboard.class);
        MessageHolder holder = new MessageHolder();
        IQuestionGettable getter = mock(IQuestionGettable.class);
        when(getter.getQuestionRoot()).thenReturn(new TestQuestionsLoader().getQuestionRoot());
        IChatLogic logic = new ChatLogic(getter, callboard);
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder, true);

        player.processMessage("Таверна");
        String logicMessage = holder.getMessage();

        assertEquals("Это таверна - тут вы можете выпить и поговорить с посетителями\n" +
                "Выберете куда пойти дальше дальше:\n" +
                " -  Главная площадь\n" +
                " -  Тролль\n" +
                " -  Подворотня", logicMessage);
    }

    @Test
    void returnToMainSquareFromTavern(){
        Callboard callboard = mock(Callboard.class);
        MessageHolder holder = new MessageHolder();
        IQuestionGettable getter = mock(IQuestionGettable.class);
        when(getter.getQuestionRoot()).thenReturn(new TestQuestionsLoader().getQuestionRoot());
        IChatLogic logic = new ChatLogic(getter, callboard);
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder, true);

        player.processMessage("Таверна");
        player.processMessage("Главная площадь");
        String logicMessage = holder.getMessage();

        assertEquals("Вы находитесь на главной площади города:\n" +
                "Выберете куда пойти дальше дальше:\n" +
                " -  Таверна\n" +
                " -  Магазин\n" +
                " -  Воровать", logicMessage);
    }

    @Test
    void goToShop(){
        Callboard callboard = mock(Callboard.class);
        MessageHolder holder = new MessageHolder();
        IQuestionGettable getter = mock(IQuestionGettable.class);
        when(getter.getQuestionRoot()).thenReturn(new TestQuestionsLoader().getQuestionRoot());
        IChatLogic logic = new ChatLogic(getter, callboard);
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder, true);

        player.processMessage("Магазин");
        String logicMessage = holder.getMessage();

        assertEquals("Тут можно купить что-либо\n" +
                "Выберете куда пойти дальше дальше:\n" +
                " -  Главная площадь", logicMessage);
    }

    @Test
    void noSuchVariant(){
        Callboard callboard = mock(Callboard.class);
        MessageHolder holder = new MessageHolder();
        IQuestionGettable getter = mock(IQuestionGettable.class);
        when(getter.getQuestionRoot()).thenReturn(new TestQuestionsLoader().getQuestionRoot());
        IChatLogic logic = new ChatLogic(getter, callboard);
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder, true);

        player.processMessage("не знаю");

        String logicMessage = holder.getMessage();

        assertEquals(logicMessage, "Такого варианта не предусмотрено");
    }

    @Test
    void checkEquip(){
        Callboard callboard = mock(Callboard.class);
        MessageHolder holder = new MessageHolder();
        IQuestionGettable getter = mock(IQuestionGettable.class);
        when(getter.getQuestionRoot()).thenReturn(new TestQuestionsLoader().getQuestionRoot());
        IChatLogic logic = new ChatLogic(getter, callboard);
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder, true);

        player.processMessage("/my_inventory");

        String logicMessage = holder.getMessage();

        assertEquals("Золото: 0\n" +
                "Опыт: 0", logicMessage);
    }

    @Test
    void stealThenCheckEquip(){
        Callboard callboard = mock(Callboard.class);
        MessageHolder holder = new MessageHolder();
        IQuestionGettable getter = mock(IQuestionGettable.class);
        when(getter.getQuestionRoot()).thenReturn(new TestQuestionsLoader().getQuestionRoot());
        IChatLogic logic = new ChatLogic(getter, callboard);
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder, true);

        player.processMessage("Воровать");
        player.processMessage("/my_inventory");

        String logicMessage = holder.getMessage();

        assertEquals("Золото: 50\n" +
                "Опыт: 10", logicMessage);
    }

    @Test
    void stealThenGetRobbed(){
        Callboard callboard = mock(Callboard.class);
        MessageHolder holder = new MessageHolder();
        IQuestionGettable getter = mock(IQuestionGettable.class);
        when(getter.getQuestionRoot()).thenReturn(new TestQuestionsLoader().getQuestionRoot());
        IChatLogic logic = new ChatLogic(getter, callboard);
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder, true);

        player.processMessage("Воровать");
        player.processMessage("Главная площадь");
        player.processMessage("Воровать");
        player.processMessage("Главная площадь");
        player.processMessage("Таверна");
        player.processMessage("Подворотня");
        player.processMessage("Оставаться");
        player.processMessage("/my_inventory");

        String logicMessage = holder.getMessage();

        assertEquals("Золото: 50\n" +
                "Опыт: 20", logicMessage);
    }
}
