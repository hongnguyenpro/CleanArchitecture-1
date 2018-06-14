package com.hieupham.absolutecleanarchitecture.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.internal.LinkedTreeMap;
import com.hieupham.domain.entity.Asset;

/**
 * Created by hieupham on 6/14/18.
 */

public class AssetModelView implements Parcelable, Mapable<Asset, AssetModelView> {

    private String id;

    private String name;

    private String fingerPrint;

    private LinkedTreeMap<String, String> metadata;

    private String registrant;

    private String status;

    private long blockNumber;

    private long blockOffset;

    private String expiresAt;

    private long offset;

    public AssetModelView() {
    }

    protected AssetModelView(Parcel in) {
        id = in.readString();
        name = in.readString();
        fingerPrint = in.readString();
        registrant = in.readString();
        status = in.readString();
        blockNumber = in.readLong();
        blockOffset = in.readLong();
        expiresAt = in.readString();
        offset = in.readLong();
        metadata = (LinkedTreeMap<String, String>) in.readSerializable();
    }

    public static final Creator<AssetModelView> CREATOR = new Creator<AssetModelView>() {
        @Override
        public AssetModelView createFromParcel(Parcel in) {
            return new AssetModelView(in);
        }

        @Override
        public AssetModelView[] newArray(int size) {
            return new AssetModelView[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(fingerPrint);
        dest.writeString(registrant);
        dest.writeString(status);
        dest.writeLong(blockNumber);
        dest.writeLong(blockOffset);
        dest.writeString(expiresAt);
        dest.writeLong(offset);
        dest.writeSerializable(metadata);
    }

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

    public LinkedTreeMap<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(LinkedTreeMap<String, String> metadata) {
        this.metadata = metadata;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssetModelView)) return false;

        AssetModelView that = (AssetModelView) o;

        if (blockNumber != that.blockNumber) return false;
        if (blockOffset != that.blockOffset) return false;
        if (offset != that.offset) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (fingerPrint != null ? !fingerPrint.equals(that.fingerPrint)
                : that.fingerPrint != null) {
            return false;
        }
        if (metadata != null ? !metadata.equals(that.metadata) : that.metadata != null) {
            return false;
        }
        if (registrant != null ? !registrant.equals(that.registrant) : that.registrant != null) {
            return false;
        }
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return expiresAt != null ? expiresAt.equals(that.expiresAt) : that.expiresAt == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
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
    public AssetModelView map(Asset asset) {
        id = asset.getId();
        name = asset.getName();
        fingerPrint = asset.getFingerPrint();
        metadata = asset.getMetadata();
        registrant = asset.getRegistrant();
        status = asset.getStatus();
        blockNumber = asset.getBlockNumber();
        blockOffset = asset.getBlockOffset();
        expiresAt = asset.getExpiresAt();
        offset = asset.getOffset();
        return this;
    }
}
