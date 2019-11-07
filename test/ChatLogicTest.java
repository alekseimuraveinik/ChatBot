/*
import datamodel.UserID;
import db.Database;
import logic.IPlayer;
import legacy.QuestionLoader;
import logic.IChatLogic;
import logic.ChatLogic;
import logic.Player;
import org.junit.jupiter.api.Test;
import auxiliary.MessageHolder;

import static org.junit.jupiter.api.Assertions.*;

class ChatLogicTest {
    private static final String filename = "new_format.txt";
    private static final String apiFilename = "firebase_api_key.json";

    @Test
    void correctGameStartingMessage(){
        MessageHolder holder = new MessageHolder();
        IChatLogic logic = new ChatLogic(new QuestionLoader(filename), new Database(apiFilename));
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder);


        String logicMessage = holder.getMessage();

        assertEquals(logicMessage, "Ты появился в волшебном мире, введи \"да\" чтобы начать игру");
    }

    @Test
    void allianceOrHordeQuestion(){
        MessageHolder holder = new MessageHolder();
        IChatLogic logic = new ChatLogic(new QuestionLoader(filename), new Database(apiFilename));
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder);

        player.processMessage("да");
        String logicMessage = holder.getMessage();

        assertEquals(logicMessage, "Ты за альянс или орду?");
    }

    @Test
    void allianceVariantChosen(){
        MessageHolder holder = new MessageHolder();
        IChatLogic logic = new ChatLogic(new QuestionLoader(filename), new Database(apiFilename));
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder);

        player.processMessage("да");
        player.processMessage("альянс");
        String logicMessage = holder.getMessage();

        assertEquals(logicMessage, "ты проиграл~");
    }

    @Test
    void hordeVariantChosen(){
        MessageHolder holder = new MessageHolder();
        IChatLogic logic = new ChatLogic(new QuestionLoader(filename), new Database(apiFilename));
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder);

        player.processMessage("да");
        player.processMessage("орда");
        String logicMessage = holder.getMessage();

        assertEquals(logicMessage, "Ты волшебник, воин, друид или вор");
    }

    @Test
    void noSuchVariant(){
        MessageHolder holder = new MessageHolder();
        IChatLogic logic = new ChatLogic(new QuestionLoader(filename), new Database(apiFilename));
        IPlayer player = new Player(logic, new UserID(0L));
        player.subscribe(holder);

        player.processMessage("не знаю");

        String logicMessage = holder.getMessage();

        assertEquals(logicMessage, "Такого варианта не предусмотрено");
    }
}
*/
