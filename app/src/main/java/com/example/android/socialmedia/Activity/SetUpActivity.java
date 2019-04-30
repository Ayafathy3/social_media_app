package com.example.android.socialmedia.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.socialmedia.R;
import com.example.android.socialmedia.classes.Post;
import com.example.android.socialmedia.classes.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
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
import id.zelory.compressor.Compressor;

public class SetUpActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    ImageView userImage;
    EditText userName;
    String name;
    Button saveSetting;
    ProgressBar progressBar;
    private Uri mainImageURI = null;
    private boolean isChanged = false;
    public Spinner spinner;
    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private String user_id;
    String _field;
    private Bitmap compressedImageFile;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        userImage = findViewById(R.id.user_image);
        userName = findViewById(R.id.user_name);
        saveSetting = findViewById(R.id.save_account_setting);
        progressBar = findViewById(R.id.progress_bar_setUp);
        android.support.v7.widget.Toolbar setupToolbar = findViewById(R.id.tool_bar_setUp);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.field, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        setSupportActionBar(setupToolbar);
        getSupportActionBar().setTitle("Account Setup");
        firebaseAuth = FirebaseAuth.getInstance();

        userImage.setOnClickListener(this);
        saveSetting.setOnClickListener(this);
        user_id = firebaseAuth.getCurrentUser().getUid();
        firebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();

        progressBar.setVisibility(View.VISIBLE);
        saveSetting.setEnabled(false);


        firebaseFirestore.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( @NonNull Task<DocumentSnapshot> task ) {

                if (task.isSuccessful()) {

                    if (task.getResult().exists()) {
                        String field = task.getResult().getString("field");
                        name = task.getResult().getString("name");

                        //           Toast.makeText(SetUpActivity.this, "your name" + name, Toast.LENGTH_SHORT).show();
                        String image = task.getResult().getString("image");
                        mainImageURI = Uri.parse(image);
                        userName.setText(name);

                        spinner.setSelection(((ArrayAdapter) spinner.getAdapter()).getPosition(field));
                        RequestOptions placeholderRequest = new RequestOptions();
                        placeholderRequest.placeholder(R.drawable.user_image);

                        Glide.with(SetUpActivity.this).setDefaultRequestOptions(placeholderRequest).load(image).into(userImage);


                    }

                } else {

                    String error = task.getException().getMessage();
                    Toast.makeText(SetUpActivity.this, "(FIRESTORE Retrieve Error) : " + error, Toast.LENGTH_LONG).show();

                }

                progressBar.setVisibility(View.INVISIBLE);
                saveSetting.setEnabled(true);

            }
        });


    }

    @Override
    public void onClick( View view ) {
        switch (view.getId()) {
            case R.id.user_image:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (ContextCompat.checkSelfPermission(SetUpActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        Toast.makeText(SetUpActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(SetUpActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                    } else {

                        BringImagePicker();
                    }

                } else {

                    BringImagePicker();
                }
                break;
            case R.id.save_account_setting:
                final String user_name = userName.getText().toString();

                if (!TextUtils.isEmpty(user_name) && mainImageURI != null) {

                    progressBar.setVisibility(View.VISIBLE);

                    if (isChanged) {

                        user_id = firebaseAuth.getCurrentUser().getUid();

                        File newImageFile = new File(mainImageURI.getPath());
                        try {

                            compressedImageFile = new Compressor(SetUpActivity.this).setMaxHeight(125).setMaxWidth(125).setQuality(50).compressToBitmap(newImageFile);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] thumbData = baos.toByteArray();

                        UploadTask image_path = storageReference.child("profile_images").child(user_id + ".jpg").putBytes(thumbData);

                        image_path.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete( @NonNull Task<UploadTask.TaskSnapshot> task ) {

                                if (task.isSuccessful()) {
                                    storeFirestore(task, user_name);

                                } else {

                                    String error = task.getException().getMessage();
                                    Toast.makeText(SetUpActivity.this, "(IMAGE Error) : " + error, Toast.LENGTH_LONG).show();

                                    progressBar.setVisibility(View.INVISIBLE);

                                }
                            }
                        });

                    } else {

                        storeFirestore(null, user_name);

                    }

                }
                break;
        }


    }

    private void BringImagePicker() {

        CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).
                setAspectRatio(1, 1).
                start(SetUpActivity.this);

    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mainImageURI = result.getUri();
                userImage.setImageURI(mainImageURI);

                //  isChanged = true;

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }

    }

    private void storeFirestore( @NonNull Task<UploadTask.TaskSnapshot> task, String user_name ) {

        Uri download_uri;

        if (task != null) {

            download_uri = task.getResult().getUploadSessionUri();

        } else {

            download_uri = mainImageURI;

        }
        Map<String, String> userMap = new HashMap<>();
        userMap.put("name", user_name);
        userMap.put("field", _field);
        userMap.put("image", download_uri.toString());


        firebaseFirestore.collection("Users").document(user_id).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete( @NonNull Task<Void> task ) {

                if (task.isSuccessful()) {

                    Toast.makeText(SetUpActivity.this, "The user Settings are updated.", Toast.LENGTH_LONG).show();
                    Intent mainIntent = new Intent(SetUpActivity.this, Home.class);
                    startActivity(mainIntent);
                    finish();
                } else {

                    String error = task.getException().getMessage();
                    Toast.makeText(SetUpActivity.this, "(FIRESTORE Error) : " + error, Toast.LENGTH_LONG).show();

                }

                progressBar.setVisibility(View.INVISIBLE);

            }
        });

    }


    // override the method for spinner

    @Override
    public void onItemSelected( AdapterView<?> adapterView, View view, int position, long l ) {
        _field = adapterView.getItemAtPosition(position).toString();

        SharedPreferences shared = getSharedPreferences("A", Context.MODE_PRIVATE); // get the set of Preferences labeled "A"
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("FIELD", _field);
        editor.putString("NAME", name);
        // Toast.makeText(this, "your name " + name, Toast.LENGTH_SHORT).show();
        editor.commit();
    }

    // override the method for spinner
    @Override
    public void onNothingSelected( AdapterView<?> adapterView ) {
        Toast.makeText(this, "please select any field", Toast.LENGTH_LONG).show();
    }

}