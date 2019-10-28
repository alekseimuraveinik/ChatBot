package datasource;

import com.google.cloud.firestore.*;
import datamodel.Node;
import db.Database;
import interfaces.IQuestionGettable;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class CloudStorageLoader implements IQuestionGettable {
    private static final String questionsCollectionName = "questions";
    private static final String questionRootDocumentName = "LA";

    @Override
    public Node getQuestionRoot() {
        try {
            for (QueryDocumentSnapshot document : Database.getInstance()
                    .collection(questionsCollectionName)
                    .get()
                    .get()
                    .getDocuments()) {

                if(document.getId().equals(questionRootDocumentName))
                    return document.toObject(Node.class);
            }

            return null;
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
