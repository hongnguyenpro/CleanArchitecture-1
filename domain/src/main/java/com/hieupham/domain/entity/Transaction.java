package com.hieupham.domain.entity;

/**
 * Created by hieupham on 6/13/18.
 */

public class Transaction implements Entity {

    private String id;

    private String owner;

    private String assetId;

    private String head;

    private String status;

    private long blockNumber;

    private long blockOffset;

    private long offset;

    private String expiredAt;

    private String payId;

    private String previousId;

    private String bitmarkId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
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

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public String getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(String expiredAt) {
        this.expiredAt = expiredAt;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getPreviousId() {
        return previousId;
    }

    public void setPreviousId(String previousId) {
        this.previousId = previousId;
    }

    public String getBitmarkId() {
        return bitmarkId;
    }

    public void setBitmarkId(String bitmarkId) {
        this.bitmarkId = bitmarkId;
    }
}
