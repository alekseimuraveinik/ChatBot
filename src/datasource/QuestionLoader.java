package datasource;

import datamodel.Node;
import interfaces.IQuestionGettable;

import java.io.IOException;

public class QuestionLoader implements IQuestionGettable {

    private String filename;
    private static final String intro = "Ты появился в волшебном мире, введи \"да\" чтобы начать игру";
    private static final String fileEncoding = "UTF-8";

    public QuestionLoader(String filename){
        this.filename = filename;
    }

    @Override
    public Node getQuestionRoot() {
        Node questionRoot = new Node("", intro);

        try (FileReader reader = new FileReader(filename, fileEncoding)) {
            String line = reader.readLine();

            while (line != null){
                String[] params = line.split(";");

                Node nodeInWhichToAdd = questionRoot;

                for (char index : params[0].toCharArray())
                    nodeInWhichToAdd = nodeInWhichToAdd.getChildByIndex(Character.getNumericValue(index));

                nodeInWhichToAdd.addChild(params[1], params[2]);

                line = reader.readLine();
            }

        }catch (IOException e) {
            return null;
        }
        return questionRoot;
    }
}
