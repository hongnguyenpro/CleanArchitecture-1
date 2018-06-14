package com.hieupham.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hieupham.domain.entity.Transaction;

/**
 * Created by jollyjoker992 on 02/06/2018.
 */

@Entity(tableName = "Transaction", foreignKeys = {
        @ForeignKey(entity = AssetData.class, parentColumns = "id", childColumns = "asset_id"),
        @ForeignKey(entity = BlockData.class, parentColumns = "number", childColumns = "block_number")
}, indices = { @Index(value = "asset_id"), @Index("block_number") })
public class TransactionData implements Parcelable, Mapable<Transaction> {

    @PrimaryKey
    @Expose
    @NonNull
    private String id;

    @Expose
    private String owner;

    @Expose
    @ColumnInfo(name = "asset_id")
    @SerializedName("asset_id")
    private String assetId;

    @Expose
    private String head;

    @Expose
    private String status;

    @Expose
    @ColumnInfo(name = "block_number")
    @SerializedName("block_number")
    private long blockNumber;

    @Expose
    @ColumnInfo(name = "block_offset")
    @SerializedName("block_offset")
    private long blockOffset;

    @Expose
    private long offset;

    @Expose
    @ColumnInfo(name = "expires_at")
    @SerializedName("expires_at")
    private String expiredAt;

    @Expose
    @ColumnInfo(name = "pay_id")
    @SerializedName("pay_id")
    private String payId;

    @Expose
    @ColumnInfo(name = "previous_id")
    @SerializedName("previous_id")
    private String previousId;

    @Expose
    @ColumnInfo(name = "bitmark_id")
    @SerializedName("bitmark_id")
    private String bitmarkId;

    public TransactionData() {
    }

    @VisibleForTesting
    @Ignore
    public TransactionData(String id, String owner, String assetId, String head, String status,
            long blockNumber, long blockOffset, long offset, String expiredAt, String payId,
            String previousId, String bitmarkId) {
        this.id = id;
        this.owner = owner;
        this.assetId = assetId;
        this.head = head;
        this.status = status;
        this.blockNumber = blockNumber;
        this.blockOffset = blockOffset;
        this.offset = offset;
        this.expiredAt = expiredAt;
        this.payId = payId;
        this.previousId = previousId;
        this.bitmarkId = bitmarkId;
    }

    @Ignore
    protected TransactionData(Parcel in) {
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

    public static final Creator<TransactionData> CREATOR = new Creator<TransactionData>() {
        @Override
        public TransactionData createFromParcel(Parcel in) {
            return new TransactionData(in);
        }

        @Override
        public TransactionData[] newArray(int size) {
            return new TransactionData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(owner);
        parcel.writeString(assetId);
        parcel.writeString(head);
        parcel.writeString(status);
        parcel.writeLong(blockNumber);
        parcel.writeLong(blockOffset);
        parcel.writeLong(offset);
        parcel.writeString(expiredAt);
        parcel.writeString(payId);
        parcel.writeString(previousId);
        parcel.writeString(bitmarkId);
    }

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
        if (!(o instanceof TransactionData)) return false;

        TransactionData that = (TransactionData) o;

        if (blockNumber != that.blockNumber) return false;
        if (blockOffset != that.blockOffset) return false;
        if (offset != that.offset) return false;
        if (!id.equals(that.id)) return false;
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
        int result = id.hashCode();
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
    public Transaction map() {
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setOwner(owner);
        transaction.setAssetId(assetId);
        transaction.setHead(head);
        transaction.setStatus(status);
        transaction.setBlockNumber(blockNumber);
        transaction.setBlockOffset(blockOffset);
        transaction.setOffset(offset);
        transaction.setExpiredAt(expiredAt);
        transaction.setPayId(payId);
        transaction.setPreviousId(previousId);
        transaction.setBitmarkId(bitmarkId);
        return transaction;
    }
}
