package com.example.msgproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class main_scroll extends AppCompatActivity {

    private ImageView btn_like;
    private boolean isLiked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_scroll);

        btn_like = findViewById(R.id.btn_like);

        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLiked = !isLiked;

                if (isLiked) {
                    btn_like.setImageResource(R.drawable.full_heart);
                } else {
                    btn_like.setImageResource(R.drawable.empty_heart);
                }
            }
        });
    }
}
