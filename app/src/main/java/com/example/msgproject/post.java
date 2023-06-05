package com.example.msgproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class post extends AppCompatActivity {

    private static final int REQUEST_IMAGE_GALLERY = 1;
    private ImageView imageViewSelectedImage;
    private EditText editTextPost;
    private Button btnPublish;

    private Uri selectedImageUri;
    private String postText;

    private ActivityResultLauncher<String> galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);

        imageViewSelectedImage = findViewById(R.id.image_view_selected_image);
        editTextPost = findViewById(R.id.edit_text_post);
        btnPublish = findViewById(R.id.btn_publish);

        galleryLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                result -> {
                    if (result != null) {
                        selectedImageUri = result;
                        imageViewSelectedImage.setImageURI(selectedImageUri);
                    }
                });

        imageViewSelectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(post.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(post.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_IMAGE_GALLERY);
                } else {
                    openGallery();
                }
            }
        });

        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postText = editTextPost.getText().toString();
                // Save the postText and selectedImageUri to your desired variables or perform further actions

                finish();
            }
        });
    }

    private void openGallery() {
        galleryLauncher.launch("image/*");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_IMAGE_GALLERY && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        }
    }
}