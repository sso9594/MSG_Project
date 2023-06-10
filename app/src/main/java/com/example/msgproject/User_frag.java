package com.example.msgproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class User_frag extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_frag, container, false);

        TextView myPostsTextView = view.findViewById(R.id.myPostsTextView);
        TextView myCommentsTextView = view.findViewById(R.id.myCommentsTextView);
        TextView myLikesTextView = view.findViewById(R.id.myLikesTextView);

        myPostsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), mypost.class);
                startActivity(intent);
            }
        });

        myCommentsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), mycomments.class);
                startActivity(intent);
            }
        });

        myLikesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), mylike.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
