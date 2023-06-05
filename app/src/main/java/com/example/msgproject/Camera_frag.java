package com.example.msgproject;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class Camera_frag extends Fragment {

    private View view;
    private static final int REQUEST_PERMISSIONS = 1;
    private ActivityResultLauncher<String[]> permissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestMultiplePermissions(),
            new ActivityResultCallback<Map<String, Boolean>>() {
                @Override
                public void onActivityResult(Map<String, Boolean> result) {
                    boolean cameraPermissionGranted = result.get(Manifest.permission.CAMERA);
                    boolean storagePermissionGranted = result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    boolean readStoragePermissionGranted = result.get(Manifest.permission.READ_EXTERNAL_STORAGE);

                    if (cameraPermissionGranted && storagePermissionGranted && readStoragePermissionGranted) {
                        launchCamera();
                    } else {
                        Toast.makeText(requireContext(), "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    private ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null && data.getExtras() != null) {
                        Bundle extras = data.getExtras();
                        Object imageObject = extras.get("data");
                        if (imageObject instanceof android.graphics.Bitmap) {
                            android.graphics.Bitmap imageBitmap = (android.graphics.Bitmap) imageObject;
                            saveImageToGallery(imageBitmap);
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "이미지 촬영에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.camera_frag, container, false);

        Button captureButton = view.findViewById(R.id.pic_btn);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermissions()) {
                    launchCamera();
                } else {
                    requestPermissions();
                }
            }
        });

        return view;
    }

    private boolean checkPermissions() {
        int permissionCamera = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA);
        int permissionStorage = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionReadStorage = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        return permissionCamera == PackageManager.PERMISSION_GRANTED && permissionStorage == PackageManager.PERMISSION_GRANTED && permissionReadStorage == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        permissionLauncher.launch(permissions);
    }

    private void launchCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            cameraLauncher.launch(intent);
        } else {
            Toast.makeText(requireContext(), "카메라 앱을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveImageToGallery(android.graphics.Bitmap imageBitmap) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + ".jpg";

        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "MSG");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        File imageFile = new File(storageDir, imageFileName);

        try {
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            imageBitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();

            // Add image to gallery
            galleryAddImage(imageFile);

            Toast.makeText(requireContext(), "이미지가 갤러리에 저장되었습니다.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "이미지 저장에 실패했습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    private void galleryAddImage(File imageFile) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(imageFile);
        mediaScanIntent.setData(contentUri);
        requireContext().sendBroadcast(mediaScanIntent);
    }
}
