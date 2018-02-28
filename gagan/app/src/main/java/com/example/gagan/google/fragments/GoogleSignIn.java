package com.example.gagan.google.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.example.gagan.google.MapActivity;
import com.example.gagan.google.R;
import com.example.gagan.google.db.DBUser;
import com.example.gagan.google.pojoclass.db.User;
import com.example.gagan.google.utils.Constant;
import com.example.gagan.google.utils.Utils;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.concurrent.Executor;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoogleSignIn extends BasePagerFragment {
    public static final String Tag = "GoogleSignIn";
    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 1;
    @BindView(R.id.signInButton)
    SignInButton signInButton;
    @BindView(R.id.profileView)
    View profileView;
    @BindView(R.id.visibility)
    View signOut;
    @BindView(R.id.fabbutton)
    View fabbutton;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.profileImage)
    ImageView profileImage;
    @BindView(R.id.fab_signOut)
    FloatingActionButton signOutBtn;
    @BindDrawable(R.drawable.ic_action_camera)
    Drawable Image_profile;
    private int rotateValue = 0;
    private boolean isVissible = false;

    public GoogleSignIn() {
    }

    ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_google_sign_in, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(getActivity(), gso);
        GoogleSignInAccount account = com.google.android.gms.auth.api.signin.GoogleSignIn.getLastSignedInAccount(getActivity());
        updateUI(account);
    }

    @OnClick(R.id.signInButton)
    public void onClickSignIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RC_SIGN_IN:
                Task<GoogleSignInAccount> task = com.google.android.gms.auth.api.signin.GoogleSignIn
                        .getSignedInAccountFromIntent(data);
                handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            updateUIWithApi(account);
        } catch (ApiException e) {
            Log.w(Tag, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUIWithApi(final GoogleSignInAccount account) {
        if (account != null) {
            Constant.currentUser = new User();
            if (account.getPhotoUrl() != null) {
                Constant.currentUser.setUri(account.getPhotoUrl().toString());
            }
            Constant.currentUser.setName(account.getDisplayName());
            Constant.currentUser.setEmail(account.getEmail());
            DBUser.AddUser(Constant.currentUser, new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {
                    Constant.AddUniqueKey(getContext(), Constant.currentUser);
                    updateUI(account);
                    Utils.Toast(getContext(), "logged in as " + Constant.currentUser.getName());

                }
            });
        }


    }

    private void updateUI(GoogleSignInAccount account) {
        signInButton.setVisibility(account == null ? View.VISIBLE : View.GONE);
        profileView.setVisibility(account == null ? View.GONE : View.VISIBLE);
        fabbutton.setVisibility(account == null ? View.GONE : View.VISIBLE);
        if (account != null) {
            if (Constant.currentUser == null) {
                DBUser.getUser(Constant.getUserKey(getContext()),
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Constant.currentUser = dataSnapshot.getValue(User.class);
                                initUi(Constant.currentUser);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        }
                );

            } else {
                initUi(Constant.currentUser);
            }


        }
    }

    private void initUi(User currentUser) {
        Crashlytics.setUserIdentifier(currentUser + " id:" + currentUser.getId());
        tv_name.setText("Name: " + currentUser.getName());
        tv_email.setText("Email: " + currentUser.getEmail());
        Picasso.with(getActivity()).load(currentUser.getUri()).error(Image_profile)
                .into(profileImage, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        profileImage.setImageDrawable(Image_profile);
                    }
                });
    }

    @OnClick(R.id.mapView)
    public void OnClickMap() {
        Intent i = new Intent(getActivity(), MapActivity.class);
        getActivity().startActivity(i);
    }

    @OnClick(R.id.fab_signOut)
    public void onClickFab() {
        rotateValue = rotateValue == 180 ? 0 : 180;
        signOutBtn.animate().rotation(rotateValue).start();
        toggle();
    }

    private void toggle() {
        isVissible = !isVissible;
        signOut.setVisibility(isVissible ? View.VISIBLE : View.GONE);
        if (isVissible) {
            signOut.animate().alpha(1).start();

        } else {
            signOut.animate().alpha(0).start();
        }
    }

    @OnClick(R.id.signOut)
    public void OnClickSignOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                DBUser.DeleteUser(Constant.currentUser, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Constant.currentUser = null;
                        Constant.RemoveUniqueKey(getContext());
                        updateUI(null);
                    }
                });

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public String getTitle() {
        return "Google sign In";
    }

    @Override
    public String getCustomTag() {
        return Tag;
    }
}
