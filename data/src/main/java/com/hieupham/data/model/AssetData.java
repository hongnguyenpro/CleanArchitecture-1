package com.hieupham.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import com.hieupham.data.source.local.api.converter.LinkedTreeMapConverter;
import com.hieupham.domain.entity.Asset;

/**
 * Created by jollyjoker992 on 03/06/2018.
 */

@Entity(tableName = "Asset")
public class AssetData implements Parcelable, Mapable<Asset> {

    @Expose
    @NonNull
    @PrimaryKey
    private String id;

    @Expose
    private String name;

    @Expose
    @SerializedName("finger_print")
    @ColumnInfo(name = "finger_print")
    private String fingerPrint;

    @Expose
    @TypeConverters(LinkedTreeMapConverter.class)
    private LinkedTreeMap<String, String> metadata;

    @Expose
    private String registrant;

    @Expose
    private String status;

    @Expose
    @SerializedName("block_number")
    @ColumnInfo(name = "block_number")
    private long blockNumber;

    @Expose
    @SerializedName("block_offset")
    @ColumnInfo(name = "block_offset")
    private long blockOffset;

    @Expose
    @SerializedName("expires_at")
    @ColumnInfo(name = "expires_at")
    private String expiresAt;

    @Expose
    private long offset;

    public AssetData() {
    }

    @VisibleForTesting
    @Ignore
    public AssetData(String id, String name, String fingerPrint,
            LinkedTreeMap<String, String> metadata, String registrant, String status,
            long blockNumber, long blockOffset, String expiresAt, long offset) {
        this.id = id;
        this.name = name;
        this.fingerPrint = fingerPrint;
        this.metadata = metadata;
        this.registrant = registrant;
        this.status = status;
        this.blockNumber = blockNumber;
        this.blockOffset = blockOffset;
        this.expiresAt = expiresAt;
        this.offset = offset;
    }

    @Ignore
    protected AssetData(Parcel in) {
        id = in.readString();
        name = in.readString();
        fingerPrint = in.readString();
        metadata = (LinkedTreeMap<String, String>) in.readSerializable();
        registrant = in.readString();
        status = in.readString();
        blockNumber = in.readLong();
        blockOffset = in.readLong();
        expiresAt = in.readString();
        offset = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(fingerPrint);
        dest.writeSerializable(metadata);
        dest.writeString(registrant);
        dest.writeString(status);
        dest.writeLong(blockNumber);
        dest.writeLong(blockOffset);
        dest.writeString(expiresAt);
        dest.writeLong(offset);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AssetData> CREATOR = new Creator<AssetData>() {
        @Override
        public AssetData createFromParcel(Parcel in) {
            return new AssetData(in);
        }

        @Override
        public AssetData[] newArray(int size) {
            return new AssetData[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(long blockNumber) {
        this.blockNumber = blockNumber;
    }

    public long getBlockOffset() {
        return blockOffset;
    }

    public void setBlockOffset(long blockOffset) {
        this.blockOffset = blockOffset;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public LinkedTreeMap<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(LinkedTreeMap<String, String> metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssetData)) return false;

        AssetData asset = (AssetData) o;

        if (blockNumber != asset.blockNumber) return false;
        if (blockOffset != asset.blockOffset) return false;
        if (offset != asset.offset) return false;
        if (!id.equals(asset.id)) return false;
        if (name != null ? !name.equals(asset.name) : asset.name != null) return false;
        if (fingerPrint != null ? !fingerPrint.equals(asset.fingerPrint)
                : asset.fingerPrint != null) {
            return false;
        }
        if (metadata != null ? !metadata.equals(asset.metadata) : asset.metadata != null) {
            return false;
        }
        if (registrant != null ? !registrant.equals(asset.registrant) : asset.registrant != null) {
            return false;
        }
        if (status != null ? !status.equals(asset.status) : asset.status != null) return false;
        return expiresAt != null ? expiresAt.equals(asset.expiresAt) : asset.expiresAt == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fingerPrint != null ? fingerPrint.hashCode() : 0);
        result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
        result = 31 * result + (registrant != null ? registrant.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (int) (blockNumber ^ (blockNumber >>> 32));
        result = 31 * result + (int) (blockOffset ^ (blockOffset >>> 32));
        result = 31 * result + (expiresAt != null ? expiresAt.hashCode() : 0);
        result = 31 * result + (int) (offset ^ (offset >>> 32));
        return result;
    }

    @Override
    public Asset map() {
        Asset asset = new Asset();
        asset.setId(id);
        asset.setName(name);
        asset.setFingerPrint(fingerPrint);
        asset.setMetadata(metadata);
        asset.setRegistrant(registrant);
        asset.setStatus(status);
        asset.setBlockNumber(blockNumber);
        asset.setBlockOffset(blockOffset);
        asset.setExpiresAt(expiresAt);
        asset.setOffset(offset);
        return asset;
    }
}
