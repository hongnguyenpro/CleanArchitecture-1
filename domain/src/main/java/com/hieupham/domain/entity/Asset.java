package com.hieupham.domain.entity;

import android.support.annotation.VisibleForTesting;
import com.google.gson.internal.LinkedTreeMap;

/**
 * Created by hieupham on 6/13/18.
 */

public class Asset implements Entity {

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

    public Asset() {
    }

    @VisibleForTesting
    public Asset(String id, String name, String fingerPrint, LinkedTreeMap<String, String> metadata,
            String registrant, String status, long blockNumber, long blockOffset, String expiresAt,
            long offset) {
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
        if (!(o instanceof Asset)) return false;

        Asset asset = (Asset) o;

        if (blockNumber != asset.blockNumber) return false;
        if (blockOffset != asset.blockOffset) return false;
        if (offset != asset.offset) return false;
        if (id != null ? !id.equals(asset.id) : asset.id != null) return false;
        if (name != null ? !name.equals(asset.name) : asset.name != null) return false;
        if (fingerPrint != null ? !fingerPrint.equals(asset.fingerPrint)
                : asset.fingerPrint != null) {
            return false;
        }
        if (metadata != null ? !metadata.equals(asset.metadata) : asset.metadata != null)
            return false;
        if (registrant != null ? !registrant.equals(asset.registrant) : asset.registrant != null) {
            return false;
        }
        if (status != null ? !status.equals(asset.status) : asset.status != null) return false;
        return expiresAt != null ? expiresAt.equals(asset.expiresAt) : asset.expiresAt == null;
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
}
