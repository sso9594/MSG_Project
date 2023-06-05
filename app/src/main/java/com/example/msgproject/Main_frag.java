package com.example.msgproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Main_frag extends Fragment {

    private View view;
    private ImageView rectangle1;
    private ImageView rectangle2;
    private Button btnCreatePost;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_frag, container, false);

        rectangle1 = view.findViewById(R.id.rectangle1);
        rectangle2 = view.findViewById(R.id.rectangle2);
        btnCreatePost = view.findViewById(R.id.btn_create_post);

        rectangle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), main_scroll.class);
                startActivity(intent);
            }
        });

        rectangle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), main_scroll.class);
                startActivity(intent);
            }
        });

        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), post.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
