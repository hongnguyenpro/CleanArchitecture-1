package com.hieupham.absolutecleanarchitecture.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.hieupham.domain.entity.Block;

/**
 * Created by hieupham on 6/14/18.
 */

public class BlockModelView implements Parcelable, Mapable<Block, BlockModelView> {

    private long number;

    private String hash;

    private String createdAt;

    protected BlockModelView(Parcel in) {
        number = in.readLong();
        hash = in.readString();
        createdAt = in.readString();
    }

    public BlockModelView() {

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

    public static final Creator<BlockModelView> CREATOR = new Creator<BlockModelView>() {
        @Override
        public BlockModelView createFromParcel(Parcel in) {
            return new BlockModelView(in);
        }

        @Override
        public BlockModelView[] newArray(int size) {
            return new BlockModelView[size];
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
        if (!(o instanceof BlockModelView)) return false;

        BlockModelView that = (BlockModelView) o;

        if (number != that.number) return false;
        if (hash != null ? !hash.equals(that.hash) : that.hash != null) return false;
        return createdAt != null ? createdAt.equals(that.createdAt) : that.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (number ^ (number >>> 32));
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public BlockModelView map(Block block) {
        number = block.getNumber();
        hash = block.getHash();
        createdAt = block.getCreatedAt();
        return this;
    }
}
