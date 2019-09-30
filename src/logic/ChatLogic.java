package logic;

import datamodel.Node;
import datamodel.Questions;
import interfaces.IChatLogic;
import interfaces.IMessageHandler;

public class ChatLogic implements IChatLogic {
    private IMessageHandler handler;

    private Node currentQuestion;

    public ChatLogic(){
        currentQuestion = new Questions().getQuestionRoot();
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

        if(nextQuestion == null)
            messageToProceed = "Такого варианта не предусмотрено";
        else {
            currentQuestion = nextQuestion;
            messageToProceed = currentQuestion.getQuestionContent();
        }
        //Здесь логика обработки ответов пользователя
        handler.handle(messageToProceed);
    }
}
