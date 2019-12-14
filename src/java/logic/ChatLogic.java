package logic;

import datamodel.QuestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


public class ChatLogic implements IChatLogic {
    private static final String CALLBOARD = "/callboard";
    private static final String ADD = "/add";
    private static final String HELP = "/help";
    private static final String INVENTORY = "/my_inventory";

    private static final String GAME_INFO = "Это игра-квест, вы можете путешествовать по сказочному миру средиземья отвечая на вопросы";

    private static final String DOUBLE_LINE_BREAK = "\n\n";
    private static final String SLASH = "/";
    private static final String SPACE = " ";

    private ICallboard callboard;

    @Autowired
    ApplicationContext context;

    public ChatLogic(ICallboard callboard) {
        this.callboard = callboard;
    }

    @Override
    public QuestMessage getNewPlayerMessage(IPlayer player) {
        player.getState().switchLogic(context.getBean(GraphWalkerLogic.class));
        return context.getBean(GraphWalkerLogic.class).getHelloMessage(player);
    }

    @Override
    public void processMessage(String userAnswer, IPlayer player) {
        if(userAnswer.startsWith(SLASH)){
            player.handle(processCommand(userAnswer, player));
            return;
        }

        player.handle(player.getState().currentLogic().getMessageAnswer(player, userAnswer));
    }

    private QuestMessage processCommand(String command, IPlayer player){
        QuestMessage answer;
        String[] commandComponents = command.split(SPACE);
        switch (commandComponents[0]){
            case HELP:
                answer = new QuestMessage(GAME_INFO + DOUBLE_LINE_BREAK);
                break;
            case CALLBOARD:
                answer = new QuestMessage(callboard.getCallboardRecords());
                break;
            case ADD:
                answer = new QuestMessage(callboard.addRecord(command.substring(4)));
                break;
            case INVENTORY:
                answer = new QuestMessage(player.getState().getPlayerInventory().stringRepresentation());
                break;
            default:
                answer = player.getState().currentLogic().processCommand(player, command);
        }
        return answer;
    }
}
