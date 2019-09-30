package datamodel;

import datasource.ParseQuestList;

public class Questions {

    private final Node questionRoot;

    public Questions(){
        ParseQuestList parser = new ParseQuestList();
        questionRoot = parser.ParseQuests("questions.txt");
    }

    public Node getQuestionRoot() {
        return questionRoot;
    }
}
