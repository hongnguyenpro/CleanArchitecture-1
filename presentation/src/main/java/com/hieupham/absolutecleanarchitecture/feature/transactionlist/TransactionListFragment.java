package com.hieupham.absolutecleanarchitecture.feature.transactionlist;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.hieupham.absolutecleanarchitecture.R;
import com.hieupham.absolutecleanarchitecture.feature.BaseSupportFragment;
import com.hieupham.absolutecleanarchitecture.feature.BaseViewModel;
import com.hieupham.absolutecleanarchitecture.feature.DialogManager;
import com.hieupham.absolutecleanarchitecture.feature.Navigator;
import com.hieupham.absolutecleanarchitecture.feature.transactiondetail.TransactionDetailFragment;
import com.hieupham.absolutecleanarchitecture.model.CompositeTransactionModelView;
import com.hieupham.absolutecleanarchitecture.utils.livedata.Resource;
import com.hieupham.absolutecleanarchitecture.utils.widget.EndlessScrollListener;
import java.util.List;
import javax.inject.Inject;

import static com.hieupham.absolutecleanarchitecture.feature.Navigator.RIGHT_LEFT;

/**
 * Created by hieupham on 5/14/18.
 */

public class TransactionListFragment extends BaseSupportFragment
        implements TransactionListAdapter.OnItemClickListener,
        EndlessScrollListener.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    ViewModel viewModel;

    @Inject
    Navigator<TransactionListFragment> navigator;

    @Inject
    DialogManager dialogManager;

    @Inject
    TransactionListAdapter adapter;

    @BindView(R.id.recycler_transaction)
    RecyclerView recyclerTransaction;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @BindView(R.id.layout_swipe)
    SwipeRefreshLayout swipeLayout;

    private EndlessScrollListener endlessScrollListener;

    public static TransactionListFragment newInstance() {
        return new TransactionListFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.fetchLatestTransactions();
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_transactionlist;
    }

    @Override
    protected void initComponents() {
        super.initComponents();
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeResources(R.color.colorAccent);

        if (recyclerTransaction.getItemDecorationCount() == 0) {
            recyclerTransaction.addItemDecoration(
                    new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        }
        recyclerTransaction.setAdapter(adapter);
        recyclerTransaction.addOnScrollListener(
                endlessScrollListener = new EndlessScrollListener(this));

        adapter.setOnItemClickListener(this);
    }

    @Override
    protected BaseViewModel viewModel() {
        return viewModel;
    }

    @Override
    protected void observe() {
        viewModel.nextTransactions().observe(this, observerNextTransactions());
        viewModel.latestTransactions().observe(this, observerLatestTransactions());
        viewModel.refreshedTransactions().observe(this, observerRefreshTransactions());
    }

    @Override
    public void onItemClicked(CompositeTransactionModelView transaction) {
        navigator.anim(RIGHT_LEFT)
                .replaceFragment(R.id.layout_root,
                        TransactionDetailFragment.newInstance(transaction), true);
    }

    @Override
    public void onLoadMore() {
        viewModel.getNextTransactions();
    }

    @Override
    public void onRefresh() {
        endlessScrollListener.reset();
        viewModel.refreshTransactions();
    }

    private Observer<Resource<List<CompositeTransactionModelView>>> observerNextTransactions() {
        return resource -> {
            progressBar.setVisibility(View.GONE);
            if (resource == null || resource.isEmpty()) return;
            if (resource.isSuccessful()) {
                List<CompositeTransactionModelView> transactions = resource.data();
                if (transactions.isEmpty()) {
                    viewModel.getNextTransactions();
                    return;
                }
                adapter.addTransactions(transactions);
            } else if (resource.isError()) {
                dialogManager.showError(resource.throwable());
            } else if (resource.isLoading()) {
                progressBar.setVisibility(View.VISIBLE);
            }
        };
    }

    private Observer<Resource<List<CompositeTransactionModelView>>> observerLatestTransactions() {
        return resource -> {
            swipeLayout.setRefreshing(false);
            progressBar.setVisibility(View.GONE);
            if (resource == null || resource.isEmpty()) return;
            if (resource.isSuccessful()) {
                List<CompositeTransactionModelView> transactions = resource.data();
                if (!transactions.isEmpty()) {
                    adapter.insertTransactions(transactions);
                }
            } else if (resource.isLoading()) {
                swipeLayout.setRefreshing(true);
            }
        };
    }

    private Observer<Resource<List<CompositeTransactionModelView>>> observerRefreshTransactions() {
        return resource -> {
            swipeLayout.setRefreshing(false);
            if (resource == null || resource.isEmpty()) return;
            if (resource.isSuccessful()) {
                List<CompositeTransactionModelView> transactions = resource.data();
                adapter.setTransactions(transactions);
            } else if (resource.isError()) {
                dialogManager.showError(resource.throwable());
            } else if (resource.isLoading()) {
                swipeLayout.setRefreshing(true);
            }
        };
    }
}
