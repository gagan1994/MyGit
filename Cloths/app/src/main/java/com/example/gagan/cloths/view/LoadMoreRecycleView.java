package com.example.gagan.cloths.view;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.gagan.cloths.interfaces.OnLoadMoreListener;
import com.squareup.picasso.Picasso;

/**
 * Created by Gagan on 5/3/2018.
 */

public class LoadMoreRecycleView extends RecyclerView {
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;
    private int pastVisibleItems;
    private int visibleThreshold = 5;


    public LoadMoreRecycleView(Context context) {
        super(context);
        initRv(context);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public LoadMoreRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initRv(context);
    }

    public LoadMoreRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initRv(context);
    }

    private void initRv(Context context) {

    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        setLayoutManager(linearLayoutManager);
        this.setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                getLinearLayoutManager((LinearLayoutManager) getLayoutManager());

            }
        });
    }

    public void setStaggerdLayoutManager(StaggeredGridLayoutManager layoutManager) {
        setLayoutManager(layoutManager);
        this.setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                getStaggerd((StaggeredGridLayoutManager) getLayoutManager());

            }
        });
    }

    private void getStaggerd(StaggeredGridLayoutManager manager) {
        int visibleItemCount = manager.getChildCount();
        int totalItemCount = manager.getItemCount();
        int[] firstVisibleItems = manager.findFirstVisibleItemPositions(null);
        if (firstVisibleItems != null && firstVisibleItems.length > 0) {
            pastVisibleItems = firstVisibleItems[0];
        }
        if ((visibleItemCount + pastVisibleItems) >= totalItemCount && !loading) {
            callLoadMore();
        }
    }

    private void callLoadMore() {
        loading = true;
        if (onLoadMoreListener != null)
            onLoadMoreListener.onLoadMore();

    }

    private void getLinearLayoutManager(LinearLayoutManager layoutManager) {
        if (layoutManager != null) {

            int totalItemCount = layoutManager.getItemCount();
            int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                callLoadMore();
            }
        }
    }
}
