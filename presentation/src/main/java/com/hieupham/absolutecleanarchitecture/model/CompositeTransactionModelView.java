package com.hieupham.absolutecleanarchitecture.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.internal.LinkedTreeMap;
import com.hieupham.absolutecleanarchitecture.R;
import com.hieupham.absolutecleanarchitecture.utils.common.DateTimeUtils;
import com.hieupham.domain.entity.CompositeTransaction;
import java.util.Map;

/**
 * Created by hieupham on 6/14/18.
 */

public class CompositeTransactionModelView
        implements Parcelable, Mapable<CompositeTransaction, CompositeTransactionModelView> {

    private TransactionModelView transaction;

    private AssetModelView asset;

    private BlockModelView block;

    private CompositeTransactionModelView() {
    }

    public static CompositeTransactionModelView instance() {
        return new CompositeTransactionModelView();
    }

    public static CompositeTransactionModelView from(TransactionModelView transaction,
            AssetModelView asset, BlockModelView block) {
        CompositeTransactionModelView modelView = new CompositeTransactionModelView();
        modelView.transaction = transaction;
        modelView.asset = asset;
        modelView.block = block;
        return modelView;
    }

    protected CompositeTransactionModelView(Parcel in) {
        transaction = in.readParcelable(TransactionModelView.class.getClassLoader());
        asset = in.readParcelable(AssetModelView.class.getClassLoader());
        block = in.readParcelable(BlockModelView.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(transaction, flags);
        dest.writeParcelable(asset, flags);
        dest.writeParcelable(block, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CompositeTransactionModelView> CREATOR =
            new Creator<CompositeTransactionModelView>() {
                @Override
                public CompositeTransactionModelView createFromParcel(Parcel in) {
                    return new CompositeTransactionModelView(in);
                }

                @Override
                public CompositeTransactionModelView[] newArray(int size) {
                    return new CompositeTransactionModelView[size];
                }
            };

    public String getId() {
        return transaction.getId();
    }

    public void setId(String id) {
        transaction.setId(id);
    }

    public String getOwner() {
        return transaction.getOwner();
    }

    public String getShortOwner() {
        String owner = transaction.getOwner();
        int length = owner.length();
        return "[" + owner.substring(0, 4) + "..." + owner.substring(length - 4, length) + "]";
    }

    public void setOwner(String owner) {
        transaction.setOwner(owner);
    }

    public String getAssetId() {
        return transaction.getAssetId();
    }

    public void setAssetId(String assetId) {
        transaction.setAssetId(assetId);
    }

    public String getHead() {
        return transaction.getHead();
    }

    public void setHead(String head) {
        transaction.setHead(head);
    }

    public String getStatus() {
        return transaction.getStatus();
    }

    public void setStatus(String status) {
        transaction.setStatus(status);
    }

    public long getBlockNumber() {
        return transaction.getBlockNumber();
    }

    public void setBlockNumber(long blockNumber) {
        transaction.setBlockNumber(blockNumber);
    }

    public long getBlockOffset() {
        return transaction.getBlockOffset();
    }

    public void setBlockOffset(long blockOffset) {
        transaction.setBlockOffset(blockOffset);
    }

    public long getOffset() {
        return transaction.getOffset();
    }

    public void setOffset(long offset) {
        transaction.setOffset(offset);
    }

    public String getExpiredAt() {
        return transaction.getExpiredAt();
    }

    public void setExpiredAt(String expiredAt) {
        transaction.setExpiredAt(expiredAt);
    }

    public String getPayId() {
        return transaction.getPayId();
    }

    public void setPayId(String payId) {
        transaction.setPayId(payId);
    }

    public String getPreviousId() {
        return transaction.getPreviousId();
    }

    public void setPreviousId(String previousId) {
        transaction.setPreviousId(previousId);
    }

    public String getBitmarkId() {
        return transaction.getBitmarkId();
    }

    public void setBitmarkId(String bitmarkId) {
        transaction.setBitmarkId(bitmarkId);
    }

    public AssetModelView getAsset() {
        return asset;
    }

    public void setAsset(AssetModelView asset) {
        this.asset = asset;
    }

    public BlockModelView getBlock() {
        return block;
    }

    public void setBlock(BlockModelView block) {
        this.block = block;
    }

    public String getBlockName(Context context) {
        String date = block == null || TextUtils.isEmpty(block.getCreatedAt()) ? ""
                : DateTimeUtils.changeFormat(block.getCreatedAt(),
                        DateTimeUtils.DATE_TIME_FORMAT_2);
        return String.format(context.getString(R.string.block_name_format),
                transaction.getBlockNumber(), (date == null ? "" : date.toUpperCase()));
    }

    public String getShortDes(Context context) {
        return isTransfer() ? String.format(context.getString(R.string.transfer_to_format),
                getShortOwner())
                : String.format(context.getString(R.string.issuance_by_format), getShortOwner());
    }

    public String getDescription(Context context) {
        return isTransfer() ? String.format(context.getString(R.string.bitmark_transfer_format),
                getShortRegistrant(), getShortOwner())
                : String.format(context.getString(R.string.issued_by_format), getOwner());
    }

    public String getBitmarkId(Context context) {
        return String.format(context.getString(R.string.bitmark_id_format),
                transaction.getBitmarkId());
    }

    public String getAssetName() {
        return asset == null ? "" : asset.getName();
    }

    public String getShortRegistrant() {
        if (asset == null) return "";
        int length = asset.getRegistrant().length();
        String registrant = asset.getRegistrant();
        return "["
                + registrant.substring(0, 4)
                + "..."
                + registrant.substring(length - 4, length)
                + "]";
    }

    public String getMetadata() {
        if (asset == null || asset.getMetadata() == null) return "";
        LinkedTreeMap<String, String> metadata = asset.getMetadata();
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : metadata.entrySet()) {
            result.append(entry.getKey().toUpperCase())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n\n");
        }
        return result.toString();
    }

    public boolean isTransfer() {
        return transaction.getPreviousId() != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositeTransactionModelView)) return false;

        CompositeTransactionModelView that = (CompositeTransactionModelView) o;

        if (transaction != null ? !transaction.equals(that.transaction)
                : that.transaction != null) {
            return false;
        }
        if (asset != null ? !asset.equals(that.asset) : that.asset != null) return false;
        return block != null ? block.equals(that.block) : that.block == null;
    }

    @Override
    public int hashCode() {
        int result = transaction != null ? transaction.hashCode() : 0;
        result = 31 * result + (asset != null ? asset.hashCode() : 0);
        result = 31 * result + (block != null ? block.hashCode() : 0);
        return result;
    }

    @Override
    public CompositeTransactionModelView map(CompositeTransaction transaction) {
        return CompositeTransactionModelView.from(
                new TransactionModelView().map(transaction.getTransaction()),
                new AssetModelView().map(transaction.getAsset()),
                new BlockModelView().map(transaction.getBlock()));
    }
}
