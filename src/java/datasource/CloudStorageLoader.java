package datasource;

import com.google.cloud.firestore.*;
import datamodel.Graph;
import db.IDatabaseLoader;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class CloudStorageLoader implements IQuestionGettable {
    private static final String questionsCollectionName = "questions";
    private static final String questionRootDocumentName = "HU";
    private IDatabaseLoader dbLoader;

    public CloudStorageLoader(IDatabaseLoader dbLoader){
        this.dbLoader = dbLoader;
    }

    @Override
    public Graph getQuestionRoot() {
        try {
            for (QueryDocumentSnapshot document : dbLoader.getFirestore()
                    .collection(questionsCollectionName)
                    .get()
                    .get()
                    .getDocuments()) {

                if(document.getId().equals(questionRootDocumentName))
                    return document.toObject(Graph.class);
            }

            return null;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
