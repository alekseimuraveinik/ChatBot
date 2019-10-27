package logic;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import datamodel.Node;
import db.Database;

import java.text.SimpleDateFormat;
import java.util.*;

public class Callboard {
    private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public String getCallboardRecords(){
        StringBuilder sb = new StringBuilder();
        try{
            Firestore db = Database.getInstance();

            ApiFuture<QuerySnapshot> query = db.collection("callboard").get();
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();


            for (QueryDocumentSnapshot document : documents) {
                sb.append(document.getString("date")).append(": ").append(document.getString("content")).append("\n");
            }

            return sb.toString();

        } catch (Exception e){
            e.printStackTrace();
        }
        return "empty";
    }

    public String addRecord(String content){
        try{
            Firestore db = Database.getInstance();

            Map<String, Object> docData = new HashMap<>();
            docData.put("date", format.format( new Date()   ));
            docData.put("content", content);
            ApiFuture<WriteResult> future = db.collection("callboard").document().set(docData);
            return "Update time : " + future.get().getUpdateTime();

        } catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}
