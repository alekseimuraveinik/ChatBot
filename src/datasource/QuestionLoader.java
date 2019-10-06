package datasource;

import datamodel.Node;
import interfaces.IQuestionGettable;

import java.io.*;

public class QuestionLoader implements IQuestionGettable {

    private String filename;
    private static final String intro = "Ты появился в волшебном мире, введи \"да\" чтобы начать игру";
    private static final String fileEncoding = "Cp1251";

    public QuestionLoader(String filename){
        this.filename = filename;
    }

    public Node getQuestionRoot(){
        Node questionRoot = new Node("", intro);

        try (FileReader reader = new FileReader(filename, fileEncoding)) {

            String line = reader.readLine();
            Node currentNode = questionRoot;
            int currentLevel = -1;

            while (line != null) {
                int numLevel = getNumLevel(line);

                if (currentLevel < numLevel){
                    String answer = addChild(currentNode, line);
                    currentNode = currentNode.getChildByAnswer(answer);
                } else if (currentLevel == numLevel){
                    String answer = addChild(currentNode.parent, line);
                    currentNode = currentNode.parent.getChildByAnswer(answer);
                } else {
                    for (int i = 0; i < currentLevel - numLevel; i++)
                        currentNode = currentNode.parent;
                    String answer = addChild(currentNode.parent, line);
                    currentNode = currentNode.parent.getChildByAnswer(answer);
                }

                currentLevel = numLevel;
                line = reader.readLine();
            }
        }catch (IOException e) {
            return null;
        }
        return questionRoot;
    }

    private String addChild(Node parent, String line){
        String[] parseLine = line.substring(1).split(";");
        parent.addChild(parseLine[0], parseLine[1]);
        return parseLine[0];
    }

    private int getNumLevel(String line){
        return Integer.parseInt(line.substring(0, 1));
    }
}
