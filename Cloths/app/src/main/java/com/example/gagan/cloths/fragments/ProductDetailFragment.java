package com.example.gagan.cloths.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gagan.cloths.R;
import com.example.gagan.cloths.adapter.ImageViewPagerSlider;
import com.example.gagan.cloths.pojo.Cloths;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailFragment extends BasicFragment {

    @BindView(R.id.profile_view_pager)
    ViewPager profile_view_pager;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.description)
    TextView description;

    private Cloths item;
    private ImageViewPagerSlider viewPagerAdapter;

    public Cloths getItem() {
        return item;
    }

    public void setItem(Cloths item) {
        this.item = item;
    }

    public ProductDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {
        viewPagerAdapter = new ImageViewPagerSlider(getContext(), item.getImages());
        profile_view_pager.setAdapter(viewPagerAdapter);
        title.setText(item.getType());
        description.setText(item.getCost() + "");

    }

    @OnClick({R.id.selectSize, R.id.iv_arrow, R.id.save, R.id.cart_detail, R.id.addcart, R.id.buyNow})
    public void onClick() {

    }

    @Override
    public String getThisTag() {
        return "ProductDetailFragment";
    }
}
