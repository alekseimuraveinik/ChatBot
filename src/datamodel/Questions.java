package datamodel;

public class Questions {

    public final Node questionRoot;

    public Questions(){
        questionRoot = new Node("", "Ты за альянс или орду?");
        questionRoot.AddChild("альянс", "Ты проиграл");
        questionRoot.AddChild("орда", "Ты волшебник, воин, друид или вор")
                .AddChild("волшебник", "Ты появился в городе, куда пойдешь - на аукцион или на арену");
    }
}
