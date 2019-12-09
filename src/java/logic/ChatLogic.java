package logic;

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
    public String getNewPlayerMessage(IPlayer player) {
        player.getState().setMessageLogic(context.getBean(GraphWalkerLogic.class));
        return context.getBean(GraphWalkerLogic.class).getHelloMessage(player);
    }

    @Override
    public void processMessage(String userAnswer, IPlayer player) {
        if(userAnswer.startsWith(SLASH)){
            player.handle(processCommand(userAnswer, player));
            return;
        }

        player.handle(player.getState().getMessageLogic().getMessageAnswer(player, userAnswer));
    }

    private String processCommand(String command, IPlayer player){
        String answer;
        String[] commandComponents = command.split(SPACE);
        switch (commandComponents[0]){
            case HELP:
                answer = GAME_INFO + DOUBLE_LINE_BREAK;
                break;
            case CALLBOARD:
                answer = callboard.getCallboardRecords();
                break;
            case ADD:
                answer = callboard.addRecord(command.substring(4));
                break;
            case INVENTORY:
                answer = player.getState().getPlayerInventory().stringRepresentation();
                break;
            default:
                answer = player.getState().getMessageLogic().processCommand(player, command);
        }
        return answer;
    }
}
