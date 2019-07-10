package com.example.tjombol;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.example.tjombol.db.TxDatabase;
import com.example.tjombol.db.TxEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class DaoTest {

    private TxDatabase txDatabase;

    @Before
    public void init(){
        txDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), TxDatabase.class).build();
    }

    @After
    public void uninit(){
        txDatabase.close();
    }
    @Test
    public void testLoadPopularArticles(){
        List<TxEntity> txEntities = new ArrayList<>();
        TxEntity entity = new TxEntity(10000, "Confirmed",
                "Employer X", "Employee 1", "INCOMING",150,"1/3/2019 at 7:30PM","Daily Payment");

        txEntities.add(entity);
        txDatabase.transactionDao().saveTransactions(txEntities);
        LiveData<List<TxEntity>> txList =  txDatabase.transactionDao().getAllTxs();
        Log.d("DAO_TEST", "testLoadTX: "+txDatabase.transactionDao().getAllTxs());
        assertNotNull(txList);
    }

}