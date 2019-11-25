package logic;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import db.IDatabaseLoader;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.*;


public class Callboard implements ICallboard{
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private static final SimpleDateFormat REPRESENT_DATE_FORMAT = new SimpleDateFormat("dd.MM '['HH:mm:ss']'");
    private IDatabaseLoader dbLoader;

    public Callboard(IDatabaseLoader dbLoader){
        this.dbLoader = dbLoader;
    }

    @Override
    public String getCallboardRecords(){
        StringBuilder sb = new StringBuilder();
        try{

            for (QueryDocumentSnapshot document :
                    dbLoader.getFirestore()
                            .collection("board")
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

    @Override
    public String addRecord(String content){
        try{
            Map<String, Object> docData = new HashMap<>();
            Date date = new Date();
            docData.put("date", getReadableDate(date));
            docData.put("content", content);
            return "Update time : " + dbLoader.getFirestore()
                    .collection("board")
                    .document(getCurrentDate(date))
                    .set(docData)
                    .get()
                    .getUpdateTime();

        } catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    private String getReadableDate(Date date){
        return REPRESENT_DATE_FORMAT.format(date);
    }

    private String getCurrentDate(Date date){
        return DATE_FORMAT.format(date);
    }
}
