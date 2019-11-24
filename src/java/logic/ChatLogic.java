package logic;

import datamodel.Graph;
import datamodel.GraphNode;
import datasource.IQuestionGettable;


public class ChatLogic implements IChatLogic {
    private static final String CALLBOARD = "/callboard";
    private static final String ADD = "/add";
    private static final String HELP = "/help";
    private static final String INVENTORY = "/my_inventory";
    private static final String UNKNOWN_COMMAND = "unknown command";

    private static final String GAME_INFO = "Это игра-квест, вы можете путешествовать по сказочному миру средиземья отвечая на вопросы";
    private static final String NO_SUCH_VARIANT = "Такого варианта не предусмотрено";

    private static final String DOUBLE_LINE_BREAK = "\n\n";
    private static final String SLASH = "/";
    private static final String SPACE = " ";

    private ICallboard callboard;
    private IQuestionGettable source;

    public ChatLogic(IQuestionGettable source, ICallboard callboard) {
        this.callboard = callboard;
        this.source = source;
    }

    @Override
    public Graph getRoot() {
        return source.getQuestionRoot();
    }

    @Override
    public void processMessage(String userAnswer, IPlayer player, Graph currentQuestion) {
        if(userAnswer.startsWith(SLASH)){
            player.handle(processCommand(userAnswer, currentQuestion, player));
            return;
        }

        String messageToProceed;

        GraphNode nextQuestion = currentQuestion.getChildByAnswer(userAnswer.toLowerCase());

         if(nextQuestion == null){
            messageToProceed = NO_SUCH_VARIANT;
        } else {
            currentQuestion.currentNode = nextQuestion;
            player.changeState(currentQuestion);
            messageToProceed = currentQuestion.getFormattedContentAndNextNodes();
            player.getPlayerInventory().AddOtherInventory(nextQuestion.getNodePrize());
        }

        player.handle(messageToProceed);

        if(nextQuestion != null && currentQuestion.finishing()) {
            currentQuestion.resetToRoot();
            player.handle(DOUBLE_LINE_BREAK + currentQuestion.getFormattedContentAndNextNodes());
        }
    }

    private String processCommand(String command, Graph currentQuestion, IPlayer player){
        String answer;
        String[] commandComponents = command.split(SPACE);
        switch (commandComponents[0]){
            case HELP:
                answer = GAME_INFO + DOUBLE_LINE_BREAK + currentQuestion.getQuestionContent();
                break;
            case CALLBOARD:
                answer = callboard.getCallboardRecords();
                break;
            case ADD:
                answer = callboard.addRecord(command.substring(4));
                break;
            case INVENTORY:
                answer = player.getPlayerInventory().ToString();
                break;
            default:
                answer = UNKNOWN_COMMAND;
        }
        return answer;
    }
}
