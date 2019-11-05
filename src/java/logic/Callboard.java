package logic;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import db.Database;
import interfaces.IDatabaseLoader;

import java.text.SimpleDateFormat;
import java.util.*;


public class Callboard {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    IDatabaseLoader dbLoader;

    public Callboard(IDatabaseLoader dbLoader){
        this.dbLoader = dbLoader;
    }

    public String getCallboardRecords(){
        StringBuilder sb = new StringBuilder();
        try{

            for (QueryDocumentSnapshot document :
                    dbLoader.getInstance()
                            .collection("callboard")
                            .get()
                            .get()
                            .getDocuments()) {
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
            Map<String, Object> docData = new HashMap<>();
            docData.put("date", getCurrentDate());
            docData.put("content", content);
            return "Update time : " + dbLoader.getInstance()
                    .collection("callboard")
                    .document()
                    .set(docData)
                    .get()
                    .getUpdateTime();

        } catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    private String getCurrentDate(){
        return DATE_FORMAT.format(new Date());
    }
}
