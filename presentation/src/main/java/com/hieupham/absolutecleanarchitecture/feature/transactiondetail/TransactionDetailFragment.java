package com.hieupham.absolutecleanarchitecture.feature.transactiondetail;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.hieupham.absolutecleanarchitecture.R;
import com.hieupham.absolutecleanarchitecture.feature.BaseSupportFragment;
import com.hieupham.absolutecleanarchitecture.feature.BaseViewModel;
import com.hieupham.absolutecleanarchitecture.model.CompositeTransactionModelView;
import com.hieupham.absolutecleanarchitecture.utils.livedata.Resource;
import javax.inject.Inject;

/**
 * Created by hieupham on 5/14/18.
 */

public class TransactionDetailFragment extends BaseSupportFragment {

    private static final String TRANSACTION = "TRANSACTION";

    @Inject
    ViewModel viewModel;

    @BindView(R.id.text_title)
    TextView textViewTitle;

    @BindView(R.id.text_asset_name)
    TextView textViewAssetName;

    @BindView(R.id.text_block_name)
    TextView textViewBlockName;

    @BindView(R.id.text_description)
    TextView textViewDescription;

    @BindView(R.id.text_bitmark_id)
    TextView textViewBitmarkId;

    @BindView(R.id.text_meta_data)
    TextView textViewMetaData;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static TransactionDetailFragment newInstance(@NonNull
            CompositeTransactionModelView transaction) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(TRANSACTION, transaction);
        TransactionDetailFragment fragment = new TransactionDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CompositeTransactionModelView transaction = getArguments().getParcelable(TRANSACTION);
        if (transaction == null) return;
        bindData(transaction);
        viewModel.getTransactionDetail(transaction.getId());
    }

    @Override
    protected void initComponents() {
        super.initComponents();
        FragmentActivity container = getActivity();
        if (container instanceof AppCompatActivity) {
            ((AppCompatActivity) container).setSupportActionBar(toolbar);
            ActionBar actionBar = ((AppCompatActivity) container).getSupportActionBar();
            if (actionBar == null) return;
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_transactiondetail;
    }

    @Override
    protected BaseViewModel viewModel() {
        return viewModel;
    }

    @Override
    protected void observe() {
        super.observe();
        viewModel.liveTransactionDetail().observe(this, observerTransactionDetail());
    }

    private void bindData(@NonNull CompositeTransactionModelView transaction) {
        Context context = getContext();
        textViewTitle.setText(
                transaction.isTransfer() ? context.getString(R.string.property_transfer)
                        : context.getString(R.string.property_issuance));
        textViewAssetName.setText(transaction.getAssetName());
        textViewBlockName.setText(transaction.getBlockName(context));
        textViewDescription.setText(transaction.getDescription(context));
        textViewBitmarkId.setText(transaction.getBitmarkId(context));
        textViewMetaData.setText(transaction.getMetadata());
    }

    private Observer<Resource<CompositeTransactionModelView>> observerTransactionDetail() {
        return resource -> {
            progressBar.setVisibility(View.INVISIBLE);
            if (resource == null || resource.isEmpty()) return;
            if (resource.isSuccessful()) {
                CompositeTransactionModelView transaction = resource.data();
                bindData(transaction);
            } else if (resource.isLoading()) {
                progressBar.setVisibility(View.VISIBLE);
            }
        };
    }
}
