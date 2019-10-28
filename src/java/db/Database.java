package db;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Database {
    private static final String firebaseApiKeyFilename = "firebase_api_key.json";

    private static Firestore db;

    public static void init(){
        try{
            createDb();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Firestore getInstance() throws IOException {
        if(db == null)
            createDb();

        return db;
    }

    private static void createDb() throws IOException {
        InputStream serviceAccount = new FileInputStream(firebaseApiKeyFilename);
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();
    }
}
