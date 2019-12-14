package logic;

import datamodel.Graph;
import datamodel.GraphNode;
import datamodel.QuestMessage;
import root.ContextHolder;

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
    public QuestMessage getMessageAnswer(IPlayer player, String message) {
        GraphNode currentQuestion = player.getState().getCurrentNode();
        QuestMessage messageToProceed = new QuestMessage();

        GraphNode nextQuestion = graph.getChildByAnswer(currentQuestion, message.toLowerCase());

        if(nextQuestion == null){
            messageToProceed.addToText(NO_SUCH_VARIANT);
        } else {
            currentQuestion = nextQuestion;
            player.changePlayerLocation(currentQuestion);
            messageToProceed.addMessage(graph.formattedContentAndNextNodes(currentQuestion));

            boolean postModify = currentQuestion.getNodeModifier().modify(player);

            if (postModify){
                IMessageLogic l = ContextHolder.get().getBean(CardPlayLogic.class);
                player.getState().switchLogic(l);
                messageToProceed = l.getHelloMessage(player);
            }
        }

        if(nextQuestion != null && currentQuestion.isDeadNode()) {
            player.changePlayerLocation(graph.getRoot());
            messageToProceed.addMessage(graph.formattedContentAndNextNodes(graph.getRoot()));
        }

        return messageToProceed;
    }

    @Override
    public QuestMessage getHelloMessage(IPlayer player) {
        return new QuestMessage(HELLO_MESSAGE + graph.formattedContentAndNextNodes(graph.getRoot()));
    }

    @Override
    public QuestMessage processCommand(IPlayer player, String command) {
        QuestMessage answer;
        if (CURRENT_LOCATION.equals(command)) {
            answer = getGraph()
                    .formattedContentAndNextNodes(player.getState().getCurrentNode());
        } else {
            answer = new QuestMessage(UNKNOWN_COMMAND);
        }

        return answer;
    }

    public Graph getGraph() { return graph; }
}
