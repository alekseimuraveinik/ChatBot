package logic;

import datamodel.GraphNode;
import datamodel.Node;
import datasource.IQuestionGettable;

import java.util.ArrayList;


public class ChatLogic implements IChatLogic {
    private static final String CALLBOARD = "/callboard";
    private static final String ADD = "/add";
    private static final String HELP = "/help";
    private static final String UNKNOWN_COMMAND = "unknown command";

    private static final String GAME_INFO = "Это игра-квест, вы можете путешествовать по сказочному миру средиземья отвечая на вопросы";
    private static final String NO_SUCH_VARIANT = "Такого варианта не предусмотрено";

    private static final String DOUBLE_LINE_BREAK = "\n\n";
    private static final String SLASH = "/";
    private static final String SPACE = " ";

    private GraphNode root;
    private ICallboard callboard;

    public ChatLogic(IQuestionGettable source, ICallboard callboard) {
        this.callboard = callboard;
        root = source.getQuestionRoot();
    }

    @Override
    public GraphNode getRoot() {
        return root;
    }

    @Override
    public void processMessage(String userAnswer, IPlayer player, GraphNode currentQuestion) {
        if(userAnswer.startsWith(SLASH)){
            player.handle(processCommand(userAnswer, currentQuestion));
            return;
        }

        String messageToProceed;

        GraphNode nextQuestion = currentQuestion.getChildByAnswer(userAnswer.toLowerCase());

         if(nextQuestion == null){
            messageToProceed = NO_SUCH_VARIANT;
        } else {
            player.changeState(nextQuestion);
            messageToProceed = nextQuestion.getFormattedContentAndNextNodes();
        }

        player.handle(messageToProceed);

        if(nextQuestion != null && nextQuestion.finishing()) {
            player.changeState(root);
            player.handle(DOUBLE_LINE_BREAK + root.getQuestionContent());
        }
    }

    private String processCommand(String command, GraphNode currentQuestion){
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
            default:
                answer = UNKNOWN_COMMAND;
        }
        return answer;
    }
}
