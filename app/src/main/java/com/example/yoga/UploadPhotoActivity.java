package com.example.yoga;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UploadPhotoActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 101;

    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private ArrayList<String> imagePaths;
    private ImageDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);

        Button btnSelect = findViewById(R.id.btn_select_image);
        Button btnView = findViewById(R.id.btn_view_images);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        dbHelper = new ImageDatabaseHelper(this);
        imagePaths = dbHelper.getAllImagePaths();
        adapter = new ImageAdapter(this, imagePaths);
        recyclerView.setAdapter(adapter);

        checkStoragePermission(); // check permission on start

        btnSelect.setOnClickListener(v -> openImagePicker());

        btnView.setOnClickListener(v -> {
            imagePaths.clear();
            imagePaths.addAll(dbHelper.getAllImagePaths());
            for (String path : imagePaths) {
                Log.d("DB Path", "Image from DB: " + path);
            }
            adapter.notifyDataSetChanged();
        });
    }

    private void checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_MEDIA_IMAGES}, STORAGE_PERMISSION_CODE);
            }
        } else {
            // Android 12 and below
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Permission", "Storage permission granted.");
            } else {
                Log.d("Permission", "Storage permission denied.");
            }
        }
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            String imagePath = getRealPathFromURI(imageUri);

            if (imagePath != null && !imagePath.isEmpty()) {
                Log.d("ImagePath", "Selected image path: " + imagePath);
                dbHelper.insertImagePath(imagePath);
                imagePaths.add(imagePath);
                adapter.notifyItemInserted(imagePaths.size() - 1);
            } else {
                Log.e("ImagePath", "Failed to get image path.");
            }
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String result = null;
        Cursor cursor = getContentResolver().query(contentUri,
                new String[]{MediaStore.Images.Media.DATA}, null, null, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    result = cursor.getString(columnIndex);
                }
            } catch (Exception e) {
                Log.e("getRealPathFromURI", "Error getting real path: " + e.getMessage());
            } finally {
                cursor.close();
            }
        }
        return result;
    }
}
