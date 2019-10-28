package logic;

import datamodel.Node;
import interfaces.IChatLogic;
import interfaces.IMessageHandler;
import interfaces.IQuestionGettable;


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

    private Node currentQuestion;
    private Node root;
    private Long chatID;

    private IMessageHandler handler;

    public ChatLogic(IQuestionGettable source, Long chatID) {
        currentQuestion = source.getQuestionRoot();
        root = currentQuestion;
        this.chatID = chatID;
    }

    @Override
    public void subscribe(IMessageHandler handler) {
        this.handler = handler;
        handler.handle(chatID, currentQuestion.getQuestionContent());
    }

    @Override
    public void processMessage(String userAnswer) {
        if(userAnswer.startsWith(SLASH)){
            handler.handle(chatID, processCommand(userAnswer));
            return;
        }

        String messageToProceed;

        Node nextQuestion = currentQuestion.getChildByAnswer(userAnswer.toLowerCase());

         if(nextQuestion == null){
            messageToProceed = NO_SUCH_VARIANT;
        } else {
            currentQuestion = nextQuestion;
            messageToProceed = currentQuestion.getQuestionContent();
        }

        handler.handle(chatID, messageToProceed);

        if(currentQuestion.isTerminating()) {
            currentQuestion = root;
            handler.handle(chatID, DOUBLE_LINE_BREAK + currentQuestion.getQuestionContent());
        }
    }

    private String processCommand(String command){
        String answer;
        String[] commandComponents = command.split(SPACE);
        switch (commandComponents[0]){
            case HELP:
                answer = GAME_INFO + DOUBLE_LINE_BREAK + currentQuestion.getQuestionContent();
                break;
            case CALLBOARD:
                answer = Callboard.getCallboardRecords();
                break;
            case ADD:
                answer = Callboard.addRecord(command.substring(4));
                break;
            default:
                answer = UNKNOWN_COMMAND;
        }
        return answer;
    }
}
