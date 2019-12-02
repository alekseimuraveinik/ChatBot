package logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class State {
    private Map<Long, IPlayer> logicDict = new HashMap<>();

    public void add(Long chatID, IPlayer player){
        logicDict.put(chatID, player);
    }

    public IPlayer get(Long chatID){
        return logicDict.get(chatID);
    }

    public boolean has(Long chatID){
        return logicDict.containsKey(chatID);
    }

    public boolean isEmpty(){
        return logicDict.isEmpty();
    }

    public Set<Map.Entry<Long, IPlayer>> entrySet(){
        return logicDict.entrySet();
    }
}
