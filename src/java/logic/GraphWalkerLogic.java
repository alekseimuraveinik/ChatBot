package logic;

import datamodel.Graph;
import datamodel.GraphNode;

public class GraphWalkerLogic implements IMessageLogic {
    private static final String HELLO_MESSAGE = "Добро пожаловать в текстовый РПГ мир\n";
    private static final String NO_SUCH_VARIANT = "Такого варианта не предусмотрено";
    private static final String DOUBLE_LINE_BREAK = "\n\n";

    private Graph graph;

    public GraphWalkerLogic(Graph worldGraph){
        graph = worldGraph;
    }

    @Override
    public String getMessageAnswer(IPlayer player, String message) {
        GraphNode currentQuestion = player.getPlayerState().getCurrentNode();
        String messageToProceed;

        GraphNode nextQuestion = graph.getChildByAnswer(currentQuestion, message.toLowerCase());

        if(nextQuestion == null){
            messageToProceed = NO_SUCH_VARIANT;
        } else {
            currentQuestion = nextQuestion;
            player.changePlayerLocation(currentQuestion);
            messageToProceed = graph.formattedContentAndNextNodes(currentQuestion);

            currentQuestion.getNodeModifier().modify(player);
        }

        if(nextQuestion != null && currentQuestion.isDeadNode()) {
            player.changePlayerLocation(graph.getRoot());
            messageToProceed += currentQuestion.getQuestionContent()
                                + DOUBLE_LINE_BREAK
                                + graph.formattedContentAndNextNodes(graph.getRoot());
        }

        return messageToProceed;
    }

    @Override
    public String getHelloMessage(IPlayer player) {
        return HELLO_MESSAGE + graph.formattedContentAndNextNodes(graph.getRoot());
    }

    public Graph getGraph() { return graph; }
}
