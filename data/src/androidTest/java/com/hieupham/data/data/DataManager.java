package com.hieupham.data.data;

import android.arch.persistence.room.Room;
import android.content.Context;
import com.hieupham.data.source.local.api.DatabaseManager;

/**
 * Created by hieupham on 5/28/18.
 */

public class DataManager {

    private DatabaseManager database;

    public DataManager(Context context) {
        database = Room.inMemoryDatabaseBuilder(context, DatabaseManager.class)
                .allowMainThreadQueries()
                .build();
    }

    /**
     * Construct master data for testing
     */
    public void setup() {

    }

    public void teardown() {
        database.clearAllTables();
        database.close();
    }

    public DatabaseManager database() {
        return database;
    }
}
