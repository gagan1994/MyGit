package com.example.gagan.cloths;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.example.gagan.cloths.adapter.ClothAdapter;
import com.example.gagan.cloths.interfaces.OnLoadMoreListener;
import com.example.gagan.cloths.interfaces.SwitchFragmentInterface;
import com.example.gagan.cloths.pojo.Cloths;
import com.example.gagan.cloths.utils.Constants;
import com.example.gagan.cloths.utils.Utils;
import com.example.gagan.cloths.view.CircleTransform;
import com.example.gagan.cloths.view.LoadMoreRecycleView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SwitchFragmentInterface {

    @BindView(R.id.image)
    ImageView profilePic;

    @BindView(R.id.rv_list)
    LoadMoreRecycleView recyclerView;
    private ClothAdapter clothAdapter;
    OnLoadMoreListener clothAdapterLoadMoreListner = new OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    clothAdapter.update(Utils.getDummyCloths());
                    recyclerView.setLoading(false);
                }
            }, 1000);
        }
    };

    @BindView(R.id.rv_list_horizontal)
    RecyclerView rv_list_horizontal;
    private ClothAdapter clothAdapterHorizontal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initMain();
        initAdapter(Utils.getDummyCloths());
    }

    private void initAdapter(List<Cloths> dummyCloths) {
        clothAdapter = new ClothAdapter(Utils.getDummyCloths(), this);
        recyclerView.setAdapter(clothAdapter);
        clothAdapterHorizontal = new ClothAdapter(Utils.getDummyCloths(), 1, this);
        rv_list_horizontal.setAdapter(clothAdapterHorizontal);
    }

    private void initMain() {

        Picasso.get().load(Constants.getInstant().getUSER().getPhotoUrl())
                .transform(new CircleTransform()).into(profilePic);
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crashlytics.getInstance().crash(); // Force a crash
                int a=0;
                int c=a/a;
            }
        });

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(4,
                        LinearLayoutManager.VERTICAL);
        recyclerView.setStaggerdLayoutManager(staggeredGridLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setOnLoadMoreListener(clothAdapterLoadMoreListner);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_list_horizontal.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void switchContent(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (getSupportFragmentManager() != null) {
            Fragment currentFragment;
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            ft.add(R.id.fragment_container2, fragment, tag);
            ft.addToBackStack("");
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
}
