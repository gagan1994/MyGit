package com.example.gagan.proj1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gagan.proj1.R;
import com.example.gagan.proj1.interfaces.UpdateUserInterface;
import com.example.gagan.proj1.pojo.User;
import com.example.gagan.proj1.widgets.CircleTransform;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gagan on 4/17/2018.
 */

public class UsersListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private final View mView;
    private final Context mContext;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_nick_name)
    TextView tv_nick_name;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.iv_profile_pic)
    ImageView iv_profile_pic;
    private User user;
    private UpdateUserInterface lisviewListner;

    public UsersListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);

    }

    public void bindUser(User user, UpdateUserInterface listViewListners) {
        this.user = user;
        tv_email.setText(user.getEmail());
        tv_name.setText(user.getName());
        tv_nick_name.setText(user.getNickName());
        Picasso.get().load(user.getImageUrl())
                .transform(new CircleTransform()).into(iv_profile_pic);
        this.lisviewListner = listViewListners;
    }

    @Override
    public void onClick(View v) {
        lisviewListner.OnClickUser(user,itemView);
    }

}
