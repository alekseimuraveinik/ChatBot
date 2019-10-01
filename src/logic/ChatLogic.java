package logic;

import datamodel.Node;
import interfaces.IChatLogic;
import interfaces.IMessageHandler;
import interfaces.IQuestionGettable;

public class ChatLogic implements IChatLogic {
    private IMessageHandler handler;

    private Node currentQuestion;
    private Node root;

    public ChatLogic(IQuestionGettable source){
        currentQuestion = source.getQuestionRoot();
        root = currentQuestion;
    }

    @Override
    public void subscribe(IMessageHandler handler) {
        this.handler = handler;
        handler.handle(currentQuestion.getQuestionContent());
    }

    @Override
    public void processMessage(String userAnswer) {
        Node nextQuestion = currentQuestion.getChildByAnswer(userAnswer);

        String messageToProceed;

        if (userAnswer.equals("/help") || userAnswer.equals("help") || userAnswer.equals("памагите")){
            messageToProceed = "Это игра-квест, вы можете путешествовать по сказочному миру средиземья отвечая на вопросы" +
            "\n\n" + currentQuestion.getQuestionContent();
        } else if(nextQuestion == null)
            messageToProceed = "Такого варианта не предусмотрено";
        else {
            currentQuestion = nextQuestion;
            messageToProceed = currentQuestion.getQuestionContent();
        }

        if(currentQuestion.isTerminating()) {
            currentQuestion = root;
            messageToProceed += "\n\n"+currentQuestion.getQuestionContent();
        }
        //Здесь логика обработки ответов пользователя
        handler.handle(messageToProceed);
    }

    @Override
    public boolean hasQuestions() {
        return !currentQuestion.isTerminating();
    }
}
