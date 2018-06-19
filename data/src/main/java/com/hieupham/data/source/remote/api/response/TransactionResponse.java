package com.hieupham.data.source.remote.api.response;

import android.support.annotation.VisibleForTesting;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hieupham.data.model.AssetData;
import com.hieupham.data.model.BlockData;
import com.hieupham.data.model.Mapable;
import com.hieupham.data.model.TransactionData;
import com.hieupham.domain.entity.CompositeTransaction;

/**
 * Created by hieupham on 6/2/18.
 */

public class TransactionResponse implements Response, Mapable<CompositeTransaction> {

    @Expose
    @SerializedName("tx")
    private TransactionData transaction;

    @Expose
    private AssetData asset;

    @Expose
    private BlockData block;

    public TransactionResponse() {
    }

    @VisibleForTesting
    public TransactionResponse(TransactionData transaction, AssetData asset, BlockData block) {
        this.transaction = transaction;
        this.asset = asset;
        this.block = block;
    }

    public TransactionData getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionData transaction) {
        this.transaction = transaction;
    }

    public AssetData getAsset() {
        return asset;
    }

    public void setAsset(AssetData asset) {
        this.asset = asset;
    }

    public BlockData getBlock() {
        return block;
    }

    public void setBlock(BlockData block) {
        this.block = block;
    }

    @Override
    public CompositeTransaction map() {
        CompositeTransaction result = new CompositeTransaction();
        result.setTransaction(transaction.map());
        result.setAsset(asset.map());
        result.setBlock(block.map());
        return result;
    }
}
