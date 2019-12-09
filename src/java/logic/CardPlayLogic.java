package logic;

import datamodel.CardPlayState;
import datamodel.Graph;
import datamodel.GraphNode;
import datamodel.Item;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.applet.AppletContext;
import java.util.HashMap;
import java.util.Random;

public class CardPlayLogic implements IMessageLogic {
    private HashMap<IPlayer, CardPlayState> playersStates;
    private Random r;

    public CardPlayLogic(){
        playersStates = new HashMap<>();
        r = new Random();
    }

    @Autowired
    ApplicationContext context;

    private static final String LOSE = "/lose";
    private static final String UNKNOWN_COMMAND = "Такой комманды не существует, или она в этой локации не поддерживается";
    private final String SPLITTER = ", ";
    private final String HELLO_MESSAGE = "Добро пожаловать в карточную игру. \nПравила: игроки в закрытую выкладывают по карте. Если сумма четная, карты отдается игроку, у которого число на карте больше, если нечетное - наоборот, если равны - карты возвращаются. Побеждает тот, кто отберет у противника все карты\nСдаться - /lose";
    private final String POST_HELLO_MESSAGE = "\nВведите номинал карты, которую хотите разыграть";
    private final String WRONG_MESSAGE_ANSWER = "Введите только номер карты";
    private final String PLAYER_WON_MOVE = "Вы победили и забираете себе карты";
    private final String PLAYER_LOSE_MOVE = "Вы проиграли и отдаете свои карты";
    private final String DRAW_MESSAGE = "Ничья, карты возвращаются";
    private final String NEXT_MOVE = "\nВаш следующий ход?";


    @Override
    public String getMessageAnswer(IPlayer player, String message) {
        CardPlayState thisPlayerState = playersStates.get(player);
        String processMessage = "";

        if (!IsParseString(message) || !thisPlayerState.cards.contains(Integer.parseInt(message))) {
            return WRONG_MESSAGE_ANSWER;
        }

        Integer playerCardNum = Integer.parseInt(message);
        Integer enemyCardNum = thisPlayerState.enemyCards.get(r.nextInt(thisPlayerState.enemyCards.size()));

        if ((playerCardNum + enemyCardNum) % 2 == 0) {
            processMessage += playGameMove(thisPlayerState, playerCardNum, enemyCardNum,
                    playerCardNum > enemyCardNum, playerCardNum < enemyCardNum);
        } else
        if ((playerCardNum + enemyCardNum) % 2 == 1) {
            processMessage += playGameMove(thisPlayerState, playerCardNum, enemyCardNum,
                    playerCardNum < enemyCardNum, playerCardNum > enemyCardNum);
        }

        if (thisPlayerState.cards.isEmpty()) {
            switchToDefaultLogic("Lose", player);
            return null;
        }

        if (thisPlayerState.enemyCards.isEmpty()) {
            switchToDefaultLogic("Won", player);
            return null;
        }

        processMessage += NEXT_MOVE + "\n";
        processMessage += getFormattedAllCards(thisPlayerState);

        return processMessage;
    }

    private void switchToDefaultLogic(String nextNodeName, IPlayer player){
        player.switchToDefaultLogic();
        player.processMessage(nextNodeName);
    }

    private String playGameMove(CardPlayState thisPlayerState, Integer playerCardNum,
                                Integer enemyCardNum, boolean wonCond, boolean loseCond) {
        if (wonCond) {
            thisPlayerState.enemyCards.remove(enemyCardNum);
            thisPlayerState.cards.add(enemyCardNum);
            return PLAYER_WON_MOVE;
        }
        if (loseCond) {
            thisPlayerState.cards.remove(playerCardNum);
            thisPlayerState.enemyCards.add(playerCardNum);
            return PLAYER_LOSE_MOVE;
        }

        return DRAW_MESSAGE;
    }

    @Override
    public String getHelloMessage(IPlayer player) {
        if(!player.getPlayerState().getPlayerInventory().items.contains(new Item("Карты", 1))) {
            switchToDefaultLogic("nocards", player);
            return "У вас нет карт!";
        }

        playersStates.put(player, new CardPlayState());

        for (int i = 0; i < 5; i++){
            playersStates.get(player).cards.add(r.nextInt(9) + 2);
            playersStates.get(player).enemyCards.add(r.nextInt(9) + 2);
        }

        return HELLO_MESSAGE + getFormattedAllCards(playersStates.get(player)) + POST_HELLO_MESSAGE;
    }

    @Override
    public String processCommand(IPlayer player, String command) {
        String answer;
        switch (command){
            case LOSE:
                answer = "Вы сдались";
                switchToDefaultLogic("Lose", player);
                break;
            default:
                answer = UNKNOWN_COMMAND;
        }

        return answer;
    }

    private String getFormattedAllCards(CardPlayState playState){
        StringBuilder str = new StringBuilder("\nСписок ваших карт:\n");

        str.append(playState.cards.get(0));
        for(int i = 1; i < playState.cards.size(); i++){
            str.append(SPLITTER);
            str.append(playState.cards.get(i));
        }
        return str.toString();
    }

    private boolean IsParseString(String str){
        if (str.length() > 2)
            return false;

        for (int i = 2; i <= 10; i++){
            if(Integer.toString(i).equals(str))
                return true;
        }

        return false;
    }
}
