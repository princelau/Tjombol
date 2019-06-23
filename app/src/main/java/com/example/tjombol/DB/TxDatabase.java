package com.example.tjombol.DB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tjombol.DB.DAOs.TransactionDao;
import com.example.tjombol.DB.Entities.TxEntity;

@Database(entities = {TxEntity.class},version = 1)
public abstract class TxDatabase extends RoomDatabase {

    //private static TxDatabase instance;

    public abstract TransactionDao transactionDao();
}
    /*
    public static synchronized TxDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TxDatabase.class, "tx_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TransactionDao transactionDao;

        private PopulateDbAsyncTask(TxDatabase db) {
            transactionDao = db.transactionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            transactionDao.insert(new TxEntity(10000, "Confirmed",
                    "Employer X", "Employee 1", "INCOMING",150,"1/3/2019 at 7:30PM","Daily Payment"));
            transactionDao.insert(new TxEntity(10001, "Confirmed",
                    "Employer X", "Employee 2", "INCOMING",200,"2/3/2019 at 7:30PM","Daily Payment"));
            transactionDao.insert(new TxEntity(10002, "Confirmed",
                    "Employer X", "Employee 3", "INCOMING",300,"3/3/2019 at 7:30PM","Daily Payment"));
            return null;
        }
    }
}
*/
    /*
public class TransactionInformation {

    public static String[] idArray = {"10000", "10001", "10002", "10003", "10004"};
    public static String[] senderArray = {"Employer X", "Employer X", "Employer X", "Employer X", "Employer X"};
    public static String[] receiverArray = {"Employee 1", "Employee 1", "Employee 1", "Employee 1", "Employee 1"};
    public static String[] typeArray = {"INCOMING", "INCOMING", "INCOMING", "INCOMING", "OUTGOING"};
    public static int[] amountArray = {150, 150, 200, 200, 200};
    public static String[] dateArray = {"1/3/2019 at 7:30PM", "2/3/2019 at 7:30PM", "3/3/2019 at 7:30PM", "4/3/2019 at 7:30PM", "5/3/2019 at 7:30PM"};
    public static String[] commentArray = {"Daily Payment", "Daily Payment", "Daily Payment", "Daily Payment", "Daily Payment"};
    public static String[] statusArray = {"Confirmed", "Confirmed", "Confirmed", "Confirmed", "Confirmed"};

}
*/

