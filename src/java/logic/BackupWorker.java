package logic;

import db.IDatabaseLoader;

import java.util.Map;

public class BackupWorker implements Runnable{

    private State state;
    private IDatabaseLoader db;

    public BackupWorker(State state, IDatabaseLoader db){
        this.state = state;
        this.db = db;
    }

    private void backupLoop() {
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            backup();
        }
    }

    private void backup() {
        if (state.isEmpty())
            return;

        try {
            for (Map.Entry<Long, IPlayer> entry : state.entrySet()) {
                String key = String.valueOf(entry.getKey());
                IPlayer value = entry.getValue();

                String result = db.getFirestore()
                        .collection("state")
                        .document(key)
                        .set(value)
                        .get()
                        .getUpdateTime().toString();

                System.out.println(result);
            }

            System.out.println("Data successfully saved");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to save data");
        }
    }

    @Override
    public void run() {
        backupLoop();
    }
}
