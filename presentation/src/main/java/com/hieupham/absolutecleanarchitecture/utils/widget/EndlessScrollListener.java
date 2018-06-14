package com.hieupham.absolutecleanarchitecture.utils.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by jollyjoker992 on 02/06/2018.
 */

public class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private static final int VISIBLE_THRESHOLD = 3;
    private int totalPreviousItem;
    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;
    private boolean isLoading = true;
    private LoadMoreListener listener;

    public EndlessScrollListener(@NonNull LoadMoreListener listener) {
        this.listener = listener;
    }

    public void setListener(@NonNull LoadMoreListener listener) {
        this.listener = listener;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        super.onScrolled(view, dx, dy);
        visibleItemCount = view.getChildCount();
        totalItemCount = view.getLayoutManager().getItemCount();

        if (view.getLayoutManager() instanceof LinearLayoutManager) {
            firstVisibleItem =
                    ((LinearLayoutManager) view.getLayoutManager()).findFirstVisibleItemPosition();
        } else if (view.getLayoutManager() instanceof GridLayoutManager) {
            firstVisibleItem =
                    ((GridLayoutManager) view.getLayoutManager()).findFirstVisibleItemPosition();
        } else {
            throw new RuntimeException("Un support this kind of LayoutManager ");
        }

        if (isLoading && totalItemCount > totalPreviousItem) {
            isLoading = false;
            totalPreviousItem = totalItemCount;
        }
        if (!isLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem
                + VISIBLE_THRESHOLD)) {
            isLoading = true;
            listener.onLoadMore();
        }
    }

    public void reset() {
        isLoading = true;
        firstVisibleItem = 0;
        totalItemCount = 0;
        visibleItemCount = 0;
        totalPreviousItem = 0;
    }

    public interface LoadMoreListener {
        void onLoadMore();
    }
}
