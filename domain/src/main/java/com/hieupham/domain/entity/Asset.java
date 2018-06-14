package com.hieupham.domain.entity;

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
}
