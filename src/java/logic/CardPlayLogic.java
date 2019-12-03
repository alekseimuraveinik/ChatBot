package logic;

import datamodel.CardPlayState;
import datamodel.Graph;
import datamodel.GraphNode;
import datamodel.Item;

import java.util.HashMap;
import java.util.Random;

public class CardPlayLogic implements IChatLogic {
    private HashMap<IPlayer, CardPlayState> playersStates = new HashMap<>();
    private Random r = new Random();

    private static final String SLASH = "/";
    private static final String SPACE = " ";
    private static final String UNKNOWN_COMMAND = "Эта команда либо сейчас не доступна, либо ее нет";
    private static final String HELP = "/help";
    private static final String INVENTORY = "/my_inventory";
    private static final String LOSE = "/lose";

    private final String SPLITTER = ", ";
    private final String HELLO_MESSAGE = "Добро пожаловать в карточную игру. \nПравила: игроки в закрытую выкладывают по карте. Если сумма четная, карты отдается игроку, у которого число на карте больше, если нечетное - наоборот, если равны - карты возвращаются. Побеждает тот, кто отберет у противника все карты\nСдаться - /lose";
    private final String POST_HELLO_MESSAGE = "\nВведите номинал карты, которую хотите разыграть";
    private final String WRONG_MESSAGE_ANSWER = "Введите только номер карты";
    private final String PLAYER_WON_MOVE = "Вы победили и забираете себе карты";
    private final String PLAYER_LOSE_MOVE = "Вы проиграли и отдаете свои карты";
    private final String DRAW_MESSAGE = "Ничья, карты возвращаются";
    private final String NEXT_MOVE = "\nВаш следующий ход?";

    @Override
    public void processMessage(String message, IPlayer player, GraphNode currentNode) {
        CardPlayState thisPlayerState = playersStates.get(player);
        String processMessage = "";

        if(message.startsWith(SLASH)){
            player.handle(processCommand(message, player));
            return;
        }

        if (!IsParseString(message) || !thisPlayerState.cards.contains(Integer.parseInt(message))) {
            player.handle(WRONG_MESSAGE_ANSWER);
            return;
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
            return;
        }

        if (thisPlayerState.enemyCards.isEmpty()) {
            switchToDefaultLogic("Won", player);
            return;
        }

        processMessage += NEXT_MOVE + "\n";
        processMessage += getFormattedAllCards(thisPlayerState);

        player.handle(processMessage);
    }

    private void switchToDefaultLogic(String nextNodeName, IPlayer player){
        player.getState().resetLogic();
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
    public Graph getGraph() {
        return null;
    }

    @Override
    public String getNewPlayerMessage(IPlayer player) {
        if(!player.getState().getPlayerInventory().items.contains(new Item("Карты", 1))) {
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

        for (Integer i = 2; i <= 10; i++){
            if(i.toString().equals(str))
                return true;
        }

        return false;
    }

    private String processCommand(String command, IPlayer player){
        String answer;
        String[] commandComponents = command.split(SPACE);
        switch (commandComponents[0]){
            case HELP:
                answer = HELLO_MESSAGE;
                break;
            case INVENTORY:
                answer = player.getState().getPlayerInventory().stringRepresentation();
                break;
            case LOSE:
                answer = "Вы сдались";
                switchToDefaultLogic("lose", player);
                break;
            default:
                answer = UNKNOWN_COMMAND;
        }
        return answer;
    }
}
