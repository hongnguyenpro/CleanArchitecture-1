package com.hieupham.data.source.remote.api.response;

import android.support.annotation.VisibleForTesting;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hieupham.data.model.AssetData;
import com.hieupham.data.model.BlockData;
import com.hieupham.data.model.Mapable;
import com.hieupham.data.model.TransactionData;
import com.hieupham.domain.entity.CompositeTransactions;
import java.util.List;

/**
 * Created by hieupham on 6/2/18.
 */

public class TransactionsResponse implements Response, Mapable<CompositeTransactions> {

    @Expose
    @SerializedName("txs")
    private List<TransactionData> transactions;

    @Expose
    private List<AssetData> assets;

    @Expose
    private List<BlockData> blocks;

    public TransactionsResponse() {
    }

    @VisibleForTesting
    public TransactionsResponse(List<TransactionData> transactions, List<AssetData> assets,
            List<BlockData> blocks) {
        this.transactions = transactions;
        this.assets = assets;
        this.blocks = blocks;
    }

    public List<TransactionData> getTransactions() {
        return transactions;
    }

    public List<AssetData> getAssets() {
        return assets;
    }

    public List<BlockData> getBlocks() {
        return blocks;
    }

    public void setTransactions(List<TransactionData> transactions) {
        this.transactions = transactions;
    }

    public void setAssets(List<AssetData> assets) {
        this.assets = assets;
    }

    public void setBlocks(List<BlockData> blocks) {
        this.blocks = blocks;
    }

    @Override
    public CompositeTransactions map() {
        return null;
    }
}
