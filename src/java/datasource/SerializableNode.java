package datasource;

import datamodel.Node;

import java.util.List;
import java.util.stream.Collectors;

public class SerializableNode extends Node {
    public SerializableNode(){

    }

    public String getAnswerHolder() {
        return answerHolder;
    }

    public void setAnswerHolder(String answerHolder) {
        this.answerHolder = answerHolder;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public List<SerializableNode> getChildren() {
            return children.stream()
                    .filter(obj -> obj instanceof SerializableNode)
                    .map(obj -> (SerializableNode) obj)
                    .collect(Collectors.toList());
    }

    public void setChildren(List<SerializableNode> children) {
        this.children = children.stream()
                .map( Node.class::cast )
                .collect(Collectors.toList());
    }
}
