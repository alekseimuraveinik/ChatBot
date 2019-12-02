package logic;

import datamodel.Graph;
import datamodel.GraphNode;
import datasource.IQuestionGettable;


public class ChatLogic implements IChatLogic {
    private static final String CALLBOARD = "/callboard";
    private static final String ADD = "/add";
    private static final String HELP = "/help";
    private static final String INVENTORY = "/my_inventory";
    private static final String CURRENT_LOCATION = "/current_location";
    private static final String UNKNOWN_COMMAND = "unknown command";

    private static final String GAME_INFO = "Это игра-квест, вы можете путешествовать по сказочному миру средиземья отвечая на вопросы";
    private static final String NO_SUCH_VARIANT = "Такого варианта не предусмотрено";

    private static final String HELLO_MESSAGE = "Добро пожаловать в текстовый РПГ мир\n";

    private static final String DOUBLE_LINE_BREAK = "\n\n";
    private static final String SLASH = "/";
    private static final String SPACE = " ";

    private ICallboard callboard;
    private Graph graph;

    public ChatLogic(IQuestionGettable source, ICallboard callboard) {
        this.callboard = callboard;
        graph = source.getQuestionRoot();
    }

    @Override
    public String getNewPlayerMessage() {
        return HELLO_MESSAGE + graph.formattedContentAndNextNodes(graph.getRoot());
    }

    @Override
    public Graph getRoot() {
        return graph;
    }

    @Override
    public void processMessage(String userAnswer, IPlayer player, GraphNode currentQuestion) {
        if(userAnswer.startsWith(SLASH)){
            player.handle(processCommand(userAnswer, player));
            return;
        }

        String messageToProceed;

        GraphNode nextQuestion = graph.getChildByAnswer(currentQuestion, userAnswer.toLowerCase());

         if(nextQuestion == null){
            messageToProceed = NO_SUCH_VARIANT;
        } else {
            currentQuestion = nextQuestion;
            player.changeState(currentQuestion);
            messageToProceed = graph.formattedContentAndNextNodes(currentQuestion);
            nextQuestion.getNodeModifier().modify(player);
        }

        player.handle(messageToProceed);

        if(nextQuestion != null && currentQuestion.isDeadNode()) {
            player.changeState(graph.getRoot());
            player.handle(DOUBLE_LINE_BREAK + graph.formattedContentAndNextNodes(graph.getRoot()));
        }
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
                answer = player.getPlayerState().getPlayerInventory().stringRepresentation();
                break;
            case CURRENT_LOCATION:
                answer = graph.formattedContentAndNextNodes(player.getPlayerState().getCurrentNode());
                break;
            default:
                answer = UNKNOWN_COMMAND;
        }
        return answer;
    }
}
