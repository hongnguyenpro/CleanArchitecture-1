package com.hieupham.domain.entity;

import android.support.annotation.VisibleForTesting;

/**
 * Created by hieupham on 6/13/18.
 */

public class CompositeTransaction implements Entity {

    private Transaction transaction;

    private Asset asset;

    private Block block;

    public CompositeTransaction() {
    }

    @VisibleForTesting
    public CompositeTransaction(Transaction transaction, Asset asset, Block block) {
        this.transaction = transaction;
        this.asset = asset;
        this.block = block;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositeTransaction)) return false;

        CompositeTransaction that = (CompositeTransaction) o;

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
}
