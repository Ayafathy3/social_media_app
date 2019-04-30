package com.example.android.socialmedia.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.socialmedia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import id.zelory.compressor.Compressor;

public class NewPost extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    ImageView postImage;
    EditText postEdit;
    Button addPost;
    private Uri postImageUri = null;
    private Toolbar newPostToolbar;
    private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private String current_user_id;
    private Bitmap compressedImageFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        current_user_id = firebaseAuth.getCurrentUser().getUid();
        postImage = findViewById(R.id.new_post_image);
        postEdit = findViewById(R.id.new_post_desc);
        addPost = findViewById(R.id.add_post);
        newPostToolbar = findViewById(R.id.tool_bar_newPost);
        progressBar = findViewById(R.id.progress_bar_newPost);

        postImage.setOnClickListener(this);
        addPost.setOnClickListener(this);
        setSupportActionBar(newPostToolbar);
        getSupportActionBar().setTitle("Add New Post");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.new_post_image:

                CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).setMinCropResultSize(512, 512).setAspectRatio(1, 1).start(NewPost.this);
                break;
            case R.id.add_post:
                uploadPost();
                break;
        }
    }

    public void uploadPost() {

        final String desc = postEdit.getText().toString();

        if (!TextUtils.isEmpty(desc) && postImageUri != null) {

            progressBar.setVisibility(View.VISIBLE);

            final String randomName = UUID.randomUUID().toString();

            // PHOTO UPLOAD
            File newImageFile = new File(postImageUri.getPath());
            try {

                compressedImageFile = new Compressor(NewPost.this).setMaxHeight(720).setMaxWidth(720).setQuality(40).compressToBitmap(newImageFile);

            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageData = baos.toByteArray();

            // PHOTO UPLOAD

            UploadTask filePath = storageReference.child("post_images").child(randomName + ".jpg").putBytes(imageData);
            filePath.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                    final String downloadUri = task.getResult().getUploadSessionUri().toString();

                    if (task.isSuccessful()) {

                        File newThumbFile = new File(postImageUri.getPath());
                        try {

                            compressedImageFile = new Compressor(NewPost.this).setMaxHeight(100).setMaxWidth(100).setQuality(1).compressToBitmap(newThumbFile);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] thumbData = baos.toByteArray();

                        UploadTask uploadTask = storageReference.child("post_images/thumbs").child(randomName + ".jpg").putBytes(thumbData);

                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                String downloadthumbUri = taskSnapshot.getUploadSessionUri().toString();

                                Map<String, Object> postMap = new HashMap<>();
                                postMap.put("image_url", downloadUri);
                                postMap.put("image_thumb", downloadthumbUri);
                                postMap.put("desc", desc);
                                postMap.put("user_id", current_user_id);
                                postMap.put("timestamp", FieldValue.serverTimestamp());

                                firebaseFirestore.collection("Posts").add(postMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {

                                        if (task.isSuccessful()) {

                                            Toast.makeText(NewPost.this, "Post was added", Toast.LENGTH_LONG).show();
                                            Intent mainIntent = new Intent(NewPost.this, Home.class);
                                            startActivity(mainIntent);
                                            finish();

                                        } else {

                                            String errorMessage = task.getException().getMessage();
                                            Toast.makeText(NewPost.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();

                                        }

                                        progressBar.setVisibility(View.INVISIBLE);

                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                //Error handling

                            }
                        });


                    } else {

                        progressBar.setVisibility(View.INVISIBLE);

                    }

                }
            });


        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                postImageUri = result.getUri();
                postImage.setImageURI(postImageUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }

    }
}

