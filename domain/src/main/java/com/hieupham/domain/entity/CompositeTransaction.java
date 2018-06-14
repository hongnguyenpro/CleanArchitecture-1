package com.hieupham.domain.entity;

/**
 * Created by hieupham on 6/13/18.
 */

public class CompositeTransaction implements Entity {

    private Transaction transaction;

    private Asset asset;

    private Block block;

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
}
