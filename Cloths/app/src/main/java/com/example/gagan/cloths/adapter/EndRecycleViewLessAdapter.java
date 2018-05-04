package com.example.gagan.cloths.adapter;

import android.support.v7.widget.RecyclerView;

import com.example.gagan.cloths.interfaces.OnLoadMoreListener;

/**
 * Created by Gagan on 5/3/2018.
 */

public abstract class EndRecycleViewLessAdapter<T extends ClothAdapter.ViewHolder> extends RecyclerView.Adapter<T> implements OnLoadMoreListener{

}
