package datamodel;

public class Questions {

    private final Node questionRoot;

    public Questions(){
        questionRoot = new Node("", "Ты за альянс или орду?");
        questionRoot.AddChild("альянс", "Ты проиграл", true);
        questionRoot.AddChild("орда", "Ты волшебник, воин, друид или вор")
                .AddChild("волшебник", "Ты появился в городе, куда пойдешь - на аукцион или на арену")
                .AddChild("арена", "Ты умер на арене", true);
    }

    public Node getQuestionRoot() {
        return questionRoot;
    }
}
