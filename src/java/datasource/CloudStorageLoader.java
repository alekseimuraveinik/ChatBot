package datasource;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import datamodel.Node;
import db.Database;
import interfaces.IQuestionGettable;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CloudStorageLoader implements IQuestionGettable {
    @Override
    public Node getQuestionRoot() {
        try {
            Firestore db = Database.getInstance();

            ApiFuture<QuerySnapshot> query = db.collection("questions").get();
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            for (QueryDocumentSnapshot document : documents) {

                if(document.getId().equals("LA"))
                    return document.toObject(Node.class);
            }

            return null;
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
