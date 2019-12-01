package db;

import com.google.cloud.firestore.Firestore;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface IDatabaseLoader {
    Firestore getFirestore();
    void write(Object obj, String documentName) throws ExecutionException, InterruptedException, IOException;
}
