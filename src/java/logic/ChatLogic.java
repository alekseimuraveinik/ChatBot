package logic;

import datamodel.Node;
import interfaces.IChatLogic;
import interfaces.IMessageHandler;
import interfaces.IMessageReceiver;
import interfaces.IQuestionGettable;


public class ChatLogic implements IChatLogic {
    private IMessageHandler handler;

    private static final String help = "help";
    private static final String callboard = "callboard";
    private static final String add = "add";
    private static final String slashHelp = "/help";
    private static final String ruHelp = "памагите";
    private static final String gameInfo = "Это игра-квест, вы можете путешествовать по сказочному миру средиземья отвечая на вопросы";
    private static final String noSuchVariant = "Такого варианта не предусмотрено";
    private static final String doubleLineBreak = "\n\n";

    private Node currentQuestion;
    private Node root;
    private Long chatID;

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
        Node nextQuestion = currentQuestion.getChildByAnswer(userAnswer);

        String messageToProceed;

        if (userAnswer.equals(help) || userAnswer.equals(slashHelp) || userAnswer.equals(ruHelp)){
            messageToProceed = gameInfo + doubleLineBreak + currentQuestion.getQuestionContent();
        } else if(userAnswer.equals(callboard)){
            messageToProceed = new Callboard().getCallboardRecords();
        } else if(userAnswer.startsWith("add")){
            messageToProceed = new Callboard().addRecord(userAnswer.substring(4));
        } else if(nextQuestion == null){
            messageToProceed = noSuchVariant;
        } else {
            currentQuestion = nextQuestion;
            messageToProceed = currentQuestion.getQuestionContent();
        }

        handler.handle(chatID, messageToProceed);

        if(currentQuestion.isTerminating()) {
            currentQuestion = root;
            messageToProceed = doubleLineBreak + currentQuestion.getQuestionContent();
            handler.handle(chatID, messageToProceed);
        }
    }
}
