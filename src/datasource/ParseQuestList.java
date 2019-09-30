package datasource;

import datamodel.Node;

import java.io.*;

public class ParseQuestList {

    public Node ParseQuests(String filename){
        Node questions = new Node("", "Ты появился в волшебном мире, введи \"да\" чтобы начать игру");
        try (FileInputStream br = new FileInputStream(new File(filename))) {
            BufferedReader isr = new  BufferedReader (new InputStreamReader(br, "Cp1251"));
            String line = isr.readLine();
            Node currentNode = questions;
            int currentLevel = -1;
            while (line != null) {
                int numLevel = Integer.parseInt(line.substring(0, 1));
                String[] parseLine = line.substring(1).split(";");
                if (currentLevel < numLevel){
                    currentNode.AddChild(parseLine[0], parseLine[1]);
                    currentNode = currentNode.getChildByAnswer(parseLine[0]);
                }
                else if (currentLevel == numLevel){
                    currentNode.parent.AddChild(parseLine[0], parseLine[1]);
                    currentNode = currentNode.parent.getChildByAnswer(parseLine[0]);
                }
                else {
                    for (int i = 0; i < currentLevel - numLevel; i++){
                        currentNode = currentNode.parent;
                    }
                    currentNode.parent.AddChild(parseLine[0], parseLine[1]);
                    currentNode = currentNode.parent.getChildByAnswer(parseLine[0]);
                }

                currentLevel = numLevel;
                line = isr.readLine();
            }
        }catch (IOException e) {
            System.out.println(e.toString());
        }
        return questions;
    }
}
