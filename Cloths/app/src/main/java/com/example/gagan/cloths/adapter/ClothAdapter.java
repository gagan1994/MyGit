package com.example.gagan.cloths.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gagan.cloths.MainActivity;
import com.example.gagan.cloths.fragments.ProductDetailFragment;
import com.example.gagan.cloths.R;
import com.example.gagan.cloths.interfaces.SwitchFragmentInterface;
import com.example.gagan.cloths.pojo.Cloths;
import com.example.gagan.cloths.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gagan on 5/3/2018.
 */

public class ClothAdapter extends RecyclerView.Adapter<ClothAdapter.ViewHolder> {
    private final List<Cloths> mDataset;
    private final SwitchFragmentInterface listner;
    private int Extra = 0;

    public ClothAdapter(List<Cloths> list, SwitchFragmentInterface mainActivity) {
        this.listner = mainActivity;
        mDataset = list;
    }

    public ClothAdapter(List<Cloths> list, int other, MainActivity mainActivity) {
        mDataset = list;
        Extra = other;
        this.listner = mainActivity;
    }

    @NonNull
    @Override
    public ClothAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ClothAdapter.ViewHolder holder, int position) {
        if (!getCondition(position)) {
            holder.showLoading();
            return;
        }
        holder.bind(mDataset.get(position));
    }

    private boolean getCondition(int position) {
        return (position < mDataset.size());
    }

    @Override
    public int getItemViewType(int position) {
        return getCondition(position) ? R.layout.cloth_adapter : R.layout.load_layout;
    }

    @Override
    public int getItemCount() {
        return mDataset.size() + Extra;
    }

    public void update(List<Cloths> dummyCloths) {
        int prevpos = mDataset.size() - Extra;
        mDataset.addAll(dummyCloths);
        notifyItemRangeChanged(prevpos, getItemCount());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.cost)
        TextView cost;
        @Nullable
        @BindView(R.id.type)
        TextView type;
        @Nullable
        @BindView(R.id.pic)
        ImageView pic;
        @Nullable
        @BindView(R.id.load)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Cloths cloths) {
            type.setText(cloths.getType());
            cost.setText("Rs. " + cloths.getCost());
            Picasso.get().load(cloths.getImageUrl()).into(pic);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductDetailFragment prodfrag = new ProductDetailFragment();
                    prodfrag.setItem(cloths);
                    listner.switchContent(prodfrag, prodfrag.getThisTag());
                }
            });
        }

        public void showLoading() {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    update(Utils.getDummyCloths());
                }
            });
        }
    }
}
