package com.kmk.motatawera.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.kmk.motatawera.R;
import com.kmk.motatawera.adapter.PostAdapter;
import com.kmk.motatawera.pojo.PostsModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment {
    RecyclerView recyclerView;
    List<PostsModel> postslist = new ArrayList<PostsModel>();
    PostAdapter padapter;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        recyclerView = view.findViewById(R.id.post_recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        padapter = new PostAdapter(getActivity(), postslist);

        recyclerView.setAdapter(padapter);
        getData();
        return view;
    }


    private void getData() {
        firebaseFirestore.collection("post")
                .addSnapshotListener((value, error) -> {
                    if (error == null) {
                        if (value == null) {
                            Toast.makeText(getActivity(), "No Data Found", Toast.LENGTH_SHORT).show();
                        } else {
                            for (DocumentChange documentChange : value.getDocumentChanges()) {
                                Toast.makeText(getActivity(), "datafound", Toast.LENGTH_SHORT).show();
                                PostsModel postsModel = documentChange.getDocument().toObject(PostsModel.class);
                                postslist.add(postsModel);
                                padapter.notifyDataSetChanged();
                            }

                        }
                    }
                });
    }
}