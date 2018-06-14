package com.hieupham.absolutecleanarchitecture.feature.transactionlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import com.hieupham.absolutecleanarchitecture.R;
import com.hieupham.absolutecleanarchitecture.model.CompositeTransactionModelView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hieupham on 5/17/18.
 */

public class TransactionListAdapter
        extends RecyclerView.Adapter<TransactionListAdapter.ViewHolder> {

    private List<CompositeTransactionModelView> transactions = new ArrayList<>();
    private OnItemClickListener listener;

    void clear() {
        transactions.clear();
    }

    void setTransactions(@NonNull List<CompositeTransactionModelView> transactions) {
        if (transactions.isEmpty()) return;
        clear();
        this.transactions.addAll(transactions);
        notifyDataSetChanged();
    }

    void insertTransactions(@NonNull List<CompositeTransactionModelView> transactions) {
        if (transactions.isEmpty()) return;
        if (this.transactions.isEmpty()) {
            this.transactions.addAll(transactions);
            notifyDataSetChanged();
        } else {
            this.transactions.addAll(0, transactions);
            notifyItemRangeInserted(0, transactions.size());
        }
    }

    void addTransactions(@NonNull List<CompositeTransactionModelView> transactions) {
        if (transactions.isEmpty()) return;
        if (this.transactions.isEmpty()) {
            this.transactions.addAll(transactions);
            notifyDataSetChanged();
        } else {
            this.transactions.addAll(transactions);
            notifyItemRangeInserted(this.transactions.size(), transactions.size());
        }
    }

    void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transactionlist, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(transactions.get(position));
    }

    @Override
    public int getItemCount() {
        return transactions == null ? 0 : transactions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        Context context;

        @BindView(R.id.text_block_name)
        TextView textViewBlockName;

        @BindView(R.id.text_asset_name)
        TextView textViewAssetName;

        @BindView(R.id.text_description)
        TextView textViewDes;

        @Nullable
        OnItemClickListener listener;

        CompositeTransactionModelView transaction;

        ViewHolder(View view, @Nullable OnItemClickListener listener) {
            super(view);
            context = view.getContext();
            ButterKnife.bind(this, view);
            this.listener = listener;
        }

        void bind(@NonNull CompositeTransactionModelView transaction) {
            this.transaction = transaction;
            textViewBlockName.setText(transaction.getBlockName(context));
            textViewDes.setText(transaction.getShortDes(context));
            textViewAssetName.setText(transaction.getAssetName());
        }

        @OnClick(R.id.layout_root)
        @Optional
        void onItemClicked() {
            if (listener == null) return;
            listener.onItemClicked(transaction);
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(CompositeTransactionModelView transaction);
    }
}
