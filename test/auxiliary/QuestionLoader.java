package auxiliary;

import datamodel.Node;
import datasource.IQuestionGettable;


public class QuestionLoader implements IQuestionGettable {

    private static final String intro = "Ты появился в волшебном мире, введи \"да\" чтобы начать игру";
    private static final String content = ";да;Ты за альянс или орду?\n" +
            "0;альянс;ты проиграл~\n" +
            "0;орда;Ты волшебник, воин, друид или вор\n" +
            "01;волшебник;Ты появился в городе, куда пойдешь - на аукцион или на арену\n" +
            "01;воин;Ты появился на арене, куда пойдешь - на аукцион или в город\n" +
            "01;друид;Ты появился в лесу, ты друид-медведь или друид-кошка\n" +
            "01;вор;тебя посадили в тюрьму~\n" +
            "012;друид-медведь;какого ты цвета, белого или афроамериканского\n" +
            "012;друид-кошка;ты сфинкс или перс\n" +
            "0120;белого;ты победил$\n" +
            "0120;афроамериканского;ты проиграл~\n" +
            "0120;негр;ты сел за расизм~\n" +
            "0121;сфинкс;у тебя украли нос, ты задохнулся и умер~\n" +
            "0121;перс;ты победил$";

    @Override
    public Node getQuestionRoot() {

        String[] lines = content.split("\n");
        Node questionRoot = new Node("", intro);

        for(String line : lines){
            String[] params = line.split(";");

            String path = params[0];

            Node nodeInWhichToAdd = questionRoot;

            for (char index : path.toCharArray())
                nodeInWhichToAdd = nodeInWhichToAdd.getChildByIndex(Character.getNumericValue(index));

            nodeInWhichToAdd.addChild(params[1], params[2]);
        }

        return questionRoot;
    }
}