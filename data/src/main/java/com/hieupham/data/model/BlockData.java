package com.hieupham.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.VisibleForTesting;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hieupham.domain.entity.Block;

/**
 * Created by jollyjoker992 on 03/06/2018.
 */

@Entity(tableName = "Block")
public class BlockData implements Parcelable, Mapable<Block> {

    @Expose
    @PrimaryKey
    private long number;

    @Expose
    private String hash;

    @Expose
    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    private String createdAt;

    public BlockData() {
    }

    @VisibleForTesting
    @Ignore
    public BlockData(long number, String hash, String createdAt) {
        this.number = number;
        this.hash = hash;
        this.createdAt = createdAt;
    }

    @Ignore
    protected BlockData(Parcel in) {
        number = in.readLong();
        hash = in.readString();
        createdAt = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(number);
        dest.writeString(hash);
        dest.writeString(createdAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BlockData> CREATOR = new Creator<BlockData>() {
        @Override
        public BlockData createFromParcel(Parcel in) {
            return new BlockData(in);
        }

        @Override
        public BlockData[] newArray(int size) {
            return new BlockData[size];
        }
    };

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockData)) return false;

        BlockData block = (BlockData) o;

        if (number != block.number) return false;
        if (hash != null ? !hash.equals(block.hash) : block.hash != null) return false;
        return createdAt != null ? createdAt.equals(block.createdAt) : block.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (number ^ (number >>> 32));
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public Block map() {
        Block block = new Block();
        block.setNumber(number);
        block.setHash(hash);
        block.setCreatedAt(createdAt);
        return block;
    }
}
