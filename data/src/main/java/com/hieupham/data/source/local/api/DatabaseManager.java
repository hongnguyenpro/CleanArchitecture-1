package com.hieupham.data.source.local.api;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.hieupham.data.model.AssetData;
import com.hieupham.data.model.BlockData;
import com.hieupham.data.model.TransactionData;
import com.hieupham.data.source.local.api.dao.AssetDao;
import com.hieupham.data.source.local.api.dao.BlockDao;
import com.hieupham.data.source.local.api.dao.TransactionDao;

/**
 * Created by hieupham on 5/14/18.
 */

@Database(entities = { TransactionData.class, AssetData.class, BlockData.class }, version = 1)
public abstract class DatabaseManager extends RoomDatabase {

    public static final String DATABASE_NAME = "bitmark_explorer_db";

    public abstract TransactionDao transactionDao();

    public abstract AssetDao assetDao();

    public abstract BlockDao blockDao();
}
