package com.hieupham.domain.entity;

import java.util.List;

/**
 * Created by hieupham on 6/13/18.
 */

public class CompositeTransactions implements Entity {

    private List<Transaction> transactions;

    private List<Asset> assets;

    private List<Block> blocks;

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
}
