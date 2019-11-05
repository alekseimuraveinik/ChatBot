package interfaces;

import com.google.cloud.firestore.Firestore;

import java.io.IOException;

public interface IDatabaseLoader {
    Firestore getFirestore() throws IOException;
}
