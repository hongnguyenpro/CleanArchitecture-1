package com.hieupham.absolutecleanarchitecture.model.mapper;

import com.hieupham.absolutecleanarchitecture.model.AssetModelView;
import com.hieupham.absolutecleanarchitecture.model.BlockModelView;
import com.hieupham.absolutecleanarchitecture.model.CompositeTransactionModelView;
import com.hieupham.absolutecleanarchitecture.model.TransactionModelView;
import com.hieupham.domain.entity.Asset;
import com.hieupham.domain.entity.Block;
import com.hieupham.domain.entity.CompositeTransactions;
import com.hieupham.domain.entity.Transaction;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class CompositeTransactionModelViewMapper extends Mapper {

    @Inject
    public CompositeTransactionModelViewMapper() {
    }

    public List<CompositeTransactionModelView> transform(
            CompositeTransactions compositeTransactions) {
        List<Transaction> transactions = compositeTransactions.getTransactions();
        List<Asset> assets = compositeTransactions.getAssets();
        List<Block> blocks = compositeTransactions.getBlocks();
        List<CompositeTransactionModelView> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            AssetModelView refAsset = null;
            BlockModelView refBlock = null;
            for (Asset asset : assets) {
                if (!transaction.getAssetId().equals(asset.getId())) continue;
                refAsset = new AssetModelView().map(asset);
                break;
            }
            for (Block block : blocks) {
                if (transaction.getBlockNumber() != block.getNumber()) continue;
                refBlock = new BlockModelView().map(block);
                break;
            }
            result.add(
                    CompositeTransactionModelView.from(new TransactionModelView().map(transaction),
                            refAsset, refBlock));
        }
        return result;
    }
}
