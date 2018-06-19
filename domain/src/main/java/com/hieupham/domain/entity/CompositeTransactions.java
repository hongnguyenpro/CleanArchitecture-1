package com.hieupham.domain.entity;

import android.support.annotation.VisibleForTesting;
import java.util.List;

/**
 * Created by hieupham on 6/13/18.
 */

public class CompositeTransactions implements Entity {

    private List<Transaction> transactions;

    private List<Asset> assets;

    private List<Block> blocks;

    public CompositeTransactions() {

    }

    @VisibleForTesting
    public CompositeTransactions(List<Transaction> transactions, List<Asset> assets,
            List<Block> blocks) {
        this.transactions = transactions;
        this.assets = assets;
        this.blocks = blocks;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositeTransactions)) return false;

        CompositeTransactions that = (CompositeTransactions) o;

        if (transactions != null ? !transactions.equals(that.transactions)
                : that.transactions != null) {
            return false;
        }
        if (assets != null ? !assets.equals(that.assets) : that.assets != null) return false;
        return blocks != null ? blocks.equals(that.blocks) : that.blocks == null;
    }

    @Override
    public int hashCode() {
        int result = transactions != null ? transactions.hashCode() : 0;
        result = 31 * result + (assets != null ? assets.hashCode() : 0);
        result = 31 * result + (blocks != null ? blocks.hashCode() : 0);
        return result;
    }
}
