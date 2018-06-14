package com.hieupham.data.source.local.api.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.hieupham.data.model.TransactionData;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by hieupham on 5/14/18.
 */

@Dao
public abstract class TransactionDao {

    @Query("SELECT * FROM `Transaction` ORDER BY block_number ASC")
    public abstract Maybe<List<TransactionData>> get();

    @Query("SELECT * FROM (SELECT * FROM `Transaction` ORDER BY block_number ASC) WHERE block_number = :block_number LIMIT :limit")
    public abstract Maybe<List<TransactionData>> get(long block_number, int limit);

    @Query("SELECT COALESCE(MAX(block_number), 0) FROM `Transaction`")
    public abstract Single<Long> getMaxBlockNumber();

    @Query("SELECT * FROM `Transaction` WHERE id = :id")
    public abstract Maybe<TransactionData> get(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void save(List<TransactionData> transactions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void save(TransactionData transaction);

    @Query("DELETE FROM `Transaction`")
    public abstract void delete();
}
