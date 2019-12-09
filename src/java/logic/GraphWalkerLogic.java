package logic;

import datamodel.Graph;
import datamodel.GraphNode;

public class GraphWalkerLogic implements IMessageLogic {
    private static final String CURRENT_LOCATION = "/current_location";
    private static final String UNKNOWN_COMMAND = "Такой комманды не существует, или она в этой локации не поддерживается";
    private static final String HELLO_MESSAGE = "Добро пожаловать в текстовый РПГ мир\n";
    private static final String NO_SUCH_VARIANT = "Такого варианта не предусмотрено";
    private static final String DOUBLE_LINE_BREAK = "\n\n";

    private Graph graph;

    public GraphWalkerLogic(Graph worldGraph){
        graph = worldGraph;
    }

    @Override
    public String getMessageAnswer(IPlayer player, String message) {
        GraphNode currentQuestion = player.getState().getCurrentNode();
        String messageToProceed;

        GraphNode nextQuestion = graph.getChildByAnswer(currentQuestion, message.toLowerCase());

        if(nextQuestion == null){
            messageToProceed = NO_SUCH_VARIANT;
        } else {
            currentQuestion = nextQuestion;
            player.changePlayerLocation(currentQuestion);
            messageToProceed = graph.formattedContentAndNextNodes(currentQuestion);

            String postModify = currentQuestion.getNodeModifier().modify(player);

            if (postModify != null)
                messageToProceed = postModify;
        }

        if(nextQuestion != null && currentQuestion.isDeadNode()) {
            player.changePlayerLocation(graph.getRoot());
            messageToProceed = currentQuestion.getQuestionContent()
                                + DOUBLE_LINE_BREAK
                                + graph.formattedContentAndNextNodes(graph.getRoot());
        }

        return messageToProceed;
    }

    @Override
    public String getHelloMessage(IPlayer player) {
        return HELLO_MESSAGE + graph.formattedContentAndNextNodes(graph.getRoot());
    }

    @Override
    public String processCommand(IPlayer player, String command) {
        String answer;
        switch (command){
            case CURRENT_LOCATION:
                answer = getGraph()
                        .formattedContentAndNextNodes(player.getState().getCurrentNode());
                break;
            default:
                answer = UNKNOWN_COMMAND;
        }

        return answer;
    }

    public Graph getGraph() { return graph; }
}
