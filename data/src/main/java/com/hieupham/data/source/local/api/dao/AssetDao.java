package com.hieupham.data.source.local.api.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.hieupham.data.model.AssetData;
import io.reactivex.Maybe;
import java.util.List;

/**
 * Created by jollyjoker992 on 03/06/2018.
 */

@Dao
public abstract class AssetDao {

    @Query("SELECT * FROM Asset ORDER BY block_number ASC")
    public abstract Maybe<List<AssetData>> get();

    @Query("SELECT * FROM Asset WHERE id = :id")
    public abstract Maybe<AssetData> get(String id);

    @Query("SELECT * FROM Asset WHERE block_number IN (:blockNumbers) ORDER BY block_number ASC")
    public abstract Maybe<List<AssetData>> get(long... blockNumbers);

    @Query("SELECT * FROM Asset WHERE id IN (:ids) ORDER BY block_number ASC")
    public abstract Maybe<List<AssetData>> get(String... ids);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void save(List<AssetData> assets);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void save(AssetData asset);

    @Query("DELETE FROM Asset")
    public abstract void delete();
}
