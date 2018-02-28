package com.example.gagan.google.viewholders;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gagan.google.R;
import com.example.gagan.google.databinding.UserDetailRowLayoutBinding;
import com.example.gagan.google.pojoclass.db.User;
import com.example.gagan.google.utils.Constant;
import com.example.gagan.google.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.logging.Handler;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.internal.Util;

/**
 * Created by Gagan on 2/15/2018.
 */

public class FirebaseUserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnTouchListener {
    @BindView(R.id.profile_pic)
    public ImageView profile_pic;
    @BindDrawable(R.drawable.ic_action_delete)
    Drawable ic_delete;
    @BindView(R.id.tv_email)
    public TextView tv_email;
    @BindView(R.id.tv_name)
    public TextView tv_name;
    @BindView(R.id.tv_phone)
    public TextView tv_phone;
    private final View mView;
    private final Context mContext;
    private final UserDetailRowLayoutBinding binding;
    private User user;
    private boolean isDelete;
    private OnDeleteClick onDeleteClick;
    @BindDrawable(R.drawable.ic_action_camera)
    Drawable no_image;

    public OnDeleteClick getOnDeleteClick() {
        return onDeleteClick;
    }

    public void setOnDeleteClick(OnDeleteClick onDeleteClick) {
        this.onDeleteClick = onDeleteClick;
    }

    public interface OnDeleteClick {
        public void deleteUser(User user);
    }

    public FirebaseUserViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        binding = null;
        mContext = itemView.getContext();
        itemView.setOnTouchListener(this);
        ButterKnife.bind(this, itemView);
        profile_pic.setOnClickListener(this);
    }

    public FirebaseUserViewHolder(UserDetailRowLayoutBinding binding) {
        super(binding.getRoot());
        mView = binding.getRoot();
        this.binding = binding;
        mContext = binding.getRoot().getContext();
        itemView.setOnClickListener(this);
        ButterKnife.bind(this, binding.getRoot());
    }

    public void bindData(User user) {
        if (binding != null)
            this.binding.setUser(user);
        else {
            tv_email.setText(user.getEmail());
            tv_name.setText(user.getName());
            tv_phone.setText(user.getPhone());
        }
        this.user = user;
        changeImage(user.getUri());
    }

    @Override
    public void onClick(View v) {
        if (user.getId() == Constant.currentUser.getId()) {
            Utils.Toast(mContext, "user id:" + Constant.currentUser.getId());
            return;
        }
        if (!isDelete) {
            isDelete = true;
            AnimatorSet anim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.flipping);
            anim.setTarget(profile_pic);
            anim.start();
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    profile_pic.setImageDrawable(ic_delete);

                }
            }, 500);
            return;
        }
        deleteUser();
    }

    private void deleteUser() {
        if (onDeleteClick != null)
            onDeleteClick.deleteUser(user);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (user.getId() == Constant.currentUser.getId()) {
            Utils.Toast(mContext, "user id:" + Constant.currentUser.getId());
            return true;
        }
        if (isDelete) {
            AnimatorSet anim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.flipping);
            anim.setTarget(profile_pic);
            anim.start();
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    changeImage(user.getUri());
                }
            }, 500);
            isDelete = false;

        }
        return false;
    }

    private void changeImage(String uri) {
        if (uri != null)
            Picasso.with(mContext).load(uri).into(profile_pic);
        else
            profile_pic.setImageDrawable(no_image);
    }
}
