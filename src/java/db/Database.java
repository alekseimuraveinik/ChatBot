package db;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Database implements IDatabaseLoader {
    private Firestore db;

    @Override
    public Firestore getFirestore() {
        if(db == null) {
            synchronized (this) {
                if(db == null) {
                    try {
                        createDb();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }

        return db;
    }

    private void createDb() throws IOException {
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        JsonObjectParser parser = new JsonObjectParser(jsonFactory);
        GenericJson fileContents = parser.parseAndClose(
                new FileInputStream("firebase_api_key.json"), UTF_8, GenericJson.class);

        fileContents.set("private_key_id", System.getenv("PRIVATE_KEY_ID"));
        fileContents.set("private_key", System.getenv("PRIVATE_KEY").replace("\\n", "\n"));

        fileContents.setFactory(jsonFactory);
        String text = fileContents.toPrettyString();
        InputStream is = new ByteArrayInputStream(text.getBytes(UTF_8));

        GoogleCredentials credentials = GoogleCredentials.fromStream(is);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(options, String.valueOf(FirebaseApp.getApps().size()));

        db = FirestoreClient.getFirestore(app);
    }

    public void write(Object obj, String documentName) throws ExecutionException, InterruptedException, IOException {
        if(db == null)
            try{
                createDb();
            } catch (Exception e){
                e.printStackTrace();
                return;
            }
        ApiFuture<WriteResult> future = db.collection("questions").document(documentName).set(obj);
        System.out.println("Update time : " + future.get().getUpdateTime());
    }
}
