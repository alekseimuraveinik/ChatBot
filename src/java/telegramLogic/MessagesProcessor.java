package telegramLogic;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import datamodel.ShortMessage;
import datamodel.UserID;
import db.IDatabaseLoader;
import logic.IChatLogic;
import logic.IMessageHandler;
import logic.IPlayer;
import logic.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MessagesProcessor implements IMessageProcessor{
    private HashMap<Long, IPlayer> logicDict;
    private IChatLogic logic;
    private IMessageHandler handler;
    private IDatabaseLoader dbLoader;

    public MessagesProcessor(IChatLogic logic, IDatabaseLoader dbLoader){
        //Runtime.getRuntime().addShutdownHook(new Thread(this::onShutdown, "Shutdown-thread"));
        this.dbLoader = dbLoader;
        this.logic = logic;
        logicDict = new HashMap<>();
    }

    @Override
    public void subscribe(IMessageHandler handler){
        this.handler = handler;
        try{
            //restoreSavedState();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void processMessage(ShortMessage message){
        if (!logicDict.containsKey(message.chatID)){
            IPlayer player = new Player(logic, new UserID(message.chatID));
            player.subscribe(handler, true);
            logicDict.put(message.chatID, player);
        }
        else {
            logicDict.get(message.chatID).processMessage(message.text);
        }
    }

    private void restoreSavedState() throws IOException, ExecutionException, InterruptedException {
        for (QueryDocumentSnapshot document :
                dbLoader.getFirestore()
                        .collection("state")
                        .get()
                        .get()
                        .getDocuments()) {

            Long id = Long.valueOf(document.getId());
            Player player = document.toObject(Player.class);
            player.subscribe(handler, false);
            player.setLogic(logic);

            logicDict.put(id, player);
        }
    }

    private void onShutdown() {
        if(logicDict.isEmpty())
            return;

        try {
            for(Map.Entry<Long, IPlayer> entry : logicDict.entrySet()) {
                String key = String.valueOf(entry.getKey());
                IPlayer value = entry.getValue();

                String result = dbLoader.getFirestore()
                        .collection("state")
                        .document(key)
                        .set(value)
                        .get()
                        .getUpdateTime().toString();
            }

            System.out.println("Data successfully saved");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to save data");
        }
    }
}
