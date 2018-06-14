package com.hieupham.data.source.local.api.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.hieupham.data.model.BlockData;
import io.reactivex.Maybe;
import java.util.List;

/**
 * Created by jollyjoker992 on 03/06/2018.
 */

@Dao
public abstract class BlockDao {

    @Query("SELECT * FROM Block ORDER BY number ASC")
    public abstract Maybe<List<BlockData>> get();

    @Query("SELECT * FROM Block WHERE number IN (:numbers) ORDER BY number ASC")
    public abstract Maybe<List<BlockData>> get(long... numbers);

    @Query("SELECT * FROM Block WHERE number = :number")
    public abstract Maybe<BlockData> get(long number);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void save(List<BlockData> blocks);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void save(BlockData block);

    @Query("DELETE FROM Block")
    public abstract void delete();
}
