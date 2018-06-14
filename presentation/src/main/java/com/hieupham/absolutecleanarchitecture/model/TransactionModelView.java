package com.hieupham.absolutecleanarchitecture.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.hieupham.domain.entity.Transaction;

/**
 * Created by hieupham on 5/22/18.
 */

public class TransactionModelView
        implements Parcelable, Mapable<Transaction, TransactionModelView> {

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

    public TransactionModelView() {
    }

    protected TransactionModelView(Parcel in) {
        id = in.readString();
        owner = in.readString();
        assetId = in.readString();
        head = in.readString();
        status = in.readString();
        blockNumber = in.readLong();
        blockOffset = in.readLong();
        offset = in.readLong();
        expiredAt = in.readString();
        payId = in.readString();
        previousId = in.readString();
        bitmarkId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(owner);
        dest.writeString(assetId);
        dest.writeString(head);
        dest.writeString(status);
        dest.writeLong(blockNumber);
        dest.writeLong(blockOffset);
        dest.writeLong(offset);
        dest.writeString(expiredAt);
        dest.writeString(payId);
        dest.writeString(previousId);
        dest.writeString(bitmarkId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TransactionModelView> CREATOR =
            new Creator<TransactionModelView>() {
                @Override
                public TransactionModelView createFromParcel(Parcel in) {
                    return new TransactionModelView(in);
                }

                @Override
                public TransactionModelView[] newArray(int size) {
                    return new TransactionModelView[size];
                }
            };

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionModelView)) return false;

        TransactionModelView that = (TransactionModelView) o;

        if (blockNumber != that.blockNumber) return false;
        if (blockOffset != that.blockOffset) return false;
        if (offset != that.offset) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (assetId != null ? !assetId.equals(that.assetId) : that.assetId != null) return false;
        if (head != null ? !head.equals(that.head) : that.head != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (expiredAt != null ? !expiredAt.equals(that.expiredAt) : that.expiredAt != null) {
            return false;
        }
        if (payId != null ? !payId.equals(that.payId) : that.payId != null) return false;
        if (previousId != null ? !previousId.equals(that.previousId) : that.previousId != null) {
            return false;
        }
        return bitmarkId != null ? bitmarkId.equals(that.bitmarkId) : that.bitmarkId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (assetId != null ? assetId.hashCode() : 0);
        result = 31 * result + (head != null ? head.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (int) (blockNumber ^ (blockNumber >>> 32));
        result = 31 * result + (int) (blockOffset ^ (blockOffset >>> 32));
        result = 31 * result + (int) (offset ^ (offset >>> 32));
        result = 31 * result + (expiredAt != null ? expiredAt.hashCode() : 0);
        result = 31 * result + (payId != null ? payId.hashCode() : 0);
        result = 31 * result + (previousId != null ? previousId.hashCode() : 0);
        result = 31 * result + (bitmarkId != null ? bitmarkId.hashCode() : 0);
        return result;
    }

    @Override
    public TransactionModelView map(Transaction transaction) {
        id = transaction.getId();
        owner = transaction.getOwner();
        assetId = transaction.getAssetId();
        head = transaction.getHead();
        status = transaction.getStatus();
        blockNumber = transaction.getBlockNumber();
        blockOffset = transaction.getBlockOffset();
        offset = transaction.getOffset();
        expiredAt = transaction.getExpiredAt();
        payId = transaction.getPayId();
        previousId = transaction.getPreviousId();
        bitmarkId = transaction.getBitmarkId();
        return this;
    }
}
