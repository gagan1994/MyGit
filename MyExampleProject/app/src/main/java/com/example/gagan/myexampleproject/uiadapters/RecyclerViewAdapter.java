package com.example.gagan.myexampleproject.uiadapters;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gagan.myexampleproject.R;
import com.example.gagan.myexampleproject.interfaces.uiinterface.HomeFragmentRecyclerModel;
import com.example.gagan.myexampleproject.pojoclass.UserClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gagan on 3/1/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final Picasso mPicasso;


    private final Context mContext;
    private List<HomeFragmentRecyclerModel> mDataSet;

    @Inject
    public RecyclerViewAdapter(Context context, Picasso picasso) {
        this.mDataSet = new ArrayList<>();
        this.mContext = context;
        this.mPicasso = picasso;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.home_fragment_rv_row, null, false);

        return new ViewHolder(v);
    }

    public void resetData(List<HomeFragmentRecyclerModel> list) {
        mDataSet.clear();
        mDataSet.addAll(list);
        notifyItemRangeChanged(0, list.size());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HomeFragmentRecyclerModel model = mDataSet.get(position);
        holder.tv_heading.setText(model.getHeading());
        holder.tv_description.setText(model.getDescription());
        mPicasso.with(mContext).load(model.getImageurl()).into(holder.iv_pic_url);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void addAll(List<UserClass> userClasses) {
        int prevPos=mDataSet.size()-1;
        this.mDataSet.addAll(userClasses);
        notifyItemMoved(prevPos,mDataSet.size()-1);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_heading)
        TextView tv_heading;
        @BindView(R.id.iv_pic_url)
        ImageView iv_pic_url;
        @BindView(R.id.tv_description)
        TextView tv_description;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
