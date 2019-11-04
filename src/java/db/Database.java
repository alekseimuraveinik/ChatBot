package db;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import interfaces.IDatabaseLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Database implements IDatabaseLoader {
    private String firebaseApiKeyFilename;

    private Firestore db;

    public Database(String firebaseApiKeyFilename){
        this.firebaseApiKeyFilename = firebaseApiKeyFilename;
    }

    public Firestore getInstance() throws IOException {
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
}
