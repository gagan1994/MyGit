package com.example.gagan.google.fragments;


import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.gagan.google.R;
import com.example.gagan.google.databinding.AddToDbLayoutBinding;
import com.example.gagan.google.db.DBUser;
import com.example.gagan.google.pojoclass.db.User;
import com.example.gagan.google.utils.Constant;
import com.example.gagan.google.utils.Utils;
import com.example.gagan.google.viewholders.FirebaseUserViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.internal.Util;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirebaseDBFragment extends BasePagerFragment implements FirebaseUserViewHolder.OnDeleteClick {

    public static final String TAG = "FirebaseDBFragment";
    private static final int SELECT_PHOTO = 1;
    private AddToDbLayoutBinding binding;
    private User user;
    @BindColor(R.color.simple_color)
    int color_simple;
    @BindColor(R.color.success_color)
    int success_color;
    @BindView(R.id.main_db)
    View main_layout;
    @BindView(R.id.rvList)
    RecyclerView rvList;
    private Uri uri;
    FirebaseRecyclerAdapter recyclerAdapter;
    private boolean isUndoClicked;
    private StorageReference imageReference;
    private final OnSuccessListener successfullAddlistner = new OnSuccessListener() {
        @Override
        public void onSuccess(Object o) {
            Utils.showSnacbar(getActivity(), main_layout,
                    "Successfully uploaded..", "", success_color
                    , null);
            recyclerAdapter.notifyDataSetChanged();
        }
    };
    private final OnSuccessListener<Void> succesfullDeleteListner = new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
            Utils.showSnacbar(getActivity(), main_layout, "user:" +
                    user.getName() + " deleted succesfully...", "", color_simple, null);
        }
    };

    public FirebaseDBFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_firebase_db, container, false);
        ButterKnife.bind(this, view);
        DBUser.keepSynced(true);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        ;
        setupAdapter();

        return view;
    }

    private void setupAdapter() {


        Query query = DBUser.getUserRef()
                .limitToLast(50);
        recyclerAdapter = new FirebaseRecyclerAdapter<User, FirebaseUserViewHolder>
                (User.class, R.layout.user_detail_row_layout, FirebaseUserViewHolder.class,
                        query) {
            @Override
            protected void populateViewHolder(FirebaseUserViewHolder viewHolder,
                                              User model, int position) {
                viewHolder.bindData(model);
                viewHolder.setIsRecyclable(false);
                viewHolder.setOnDeleteClick(FirebaseDBFragment.this);
            }


        };
        rvList.setAdapter(recyclerAdapter);
    }

    @OnClick(R.id.fab_add)
    public void onClickAdd() {
        showAddDialog();
    }

    private void showAddDialog() {
        user = new User();
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()),
                R.layout.add_to_db_layout, null, false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setView(binding.getRoot());
        binding.setThisUser(user);
        binding.addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.Valid()) {
                    Utils.showSnacbar(getActivity(), main_layout,
                            "uploading..", "",
                            color_simple, null);
                    if (uri != null) {
                        addImageAndInser(user, uri);
                    } else {
                        inserData(user);
                    }
                    dialog.dismiss();
                } else {
                    String toastDisplayText = user.validString();
                    Utils.Toast(getActivity(), toastDisplayText);

                }
                //
            }
        });
        dialog.show();
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void addImageAndInser(final User user, Uri uri) {
        DBUser.AddUserWithImage(user, uri, successfullAddlistner);
    }

    private void inserData(User user) {
        uri = null;
        DBUser.AddUser(user, successfullAddlistner);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            switch (requestCode) {
                case SELECT_PHOTO:
                    selectPhoto(data);
                    break;
            }
    }

    private void selectPhoto(Intent data) {
        try {
            final Uri imageUri = data.getData();
            final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            this.uri = imageUri;
            binding.addImage.setImageBitmap(selectedImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTitle() {
        return "Database";
    }

    @Override
    public String getCustomTag() {
        return TAG;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recyclerAdapter.cleanup();
    }

    @Override
    public void deleteUser(final User user) {
        isUndoClicked = false;
        Utils.showSnacbar(getActivity(), main_layout, "deleting user:" +
                user.getName() + "...", "Undo", color_simple, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isUndoClicked = true;
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isUndoClicked) return;
                if (user.getUri() != null) {
                    imageReference.child(user.getId()).delete();
                }
                confirmDeleteUser(user);

            }
        }, 1000);

    }

    private void confirmDeleteUser(final User user) {

            DBUser.DeleteUser(user, succesfullDeleteListner);
    }
}
