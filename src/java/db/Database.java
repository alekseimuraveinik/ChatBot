package db;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

public class Database implements IDatabaseLoader {
    private String firebaseApiKeyFilename;
    private Firestore db;

    public Database(String firebaseApiKeyFilename){
        this.firebaseApiKeyFilename = firebaseApiKeyFilename;
    }

    @Override
    public Firestore getFirestore() throws IOException {
        if(db == null) {
            synchronized (this) {
                if(db == null) {
                    createDb();
                }
            }
        }

        return db;
    }

    private void createDb() throws IOException {
        InputStream serviceAccount = new FileInputStream(firebaseApiKeyFilename);
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(options, String.valueOf(FirebaseApp.getApps().size()));

        db = FirestoreClient.getFirestore(app);
    }

    public void write(Object obj, String documentName) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = db.collection("questions2").document(documentName).set(obj);
        System.out.println("Update time : " + future.get().getUpdateTime());
    }
}
