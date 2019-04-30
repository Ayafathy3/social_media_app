package com.example.android.socialmedia.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.socialmedia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    EditText email, password, confirmPassword;
    Button createAccount;
    TextView haveAccount;
    private FirebaseAuth mAuth;

    // on create method , the method which executed firstly when the app run on phone
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // find the view by id
        progressBar = findViewById(R.id.progress_bar);
        email = findViewById(R.id.user_email);
        password = findViewById(R.id.new_password);
        confirmPassword = findViewById(R.id.confirm_password);
        createAccount = findViewById(R.id.create_new_account);

        haveAccount = findViewById(R.id.have_account_text);
        mAuth = FirebaseAuth.getInstance();
        createAccount.setOnClickListener(this);
        haveAccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_new_account:
                signUp();
                break;

            case R.id.have_account_text:
                startActivity(new Intent(SignUp.this, LogIn.class));
                finish();
                break;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {

            sendToHome();

        }

    }

    private void sendToHome() {
        Intent intent = new Intent(SignUp.this, Home.class);
        startActivity(intent);
        finish();
    }

    public void signUp() {

        String userPassword = password.getText().toString();
        String userConfirmPassword = confirmPassword.getText().toString();
        String userEmail = email.getText().toString().trim();

        if (!TextUtils.isEmpty(userPassword) && !TextUtils.isEmpty(userConfirmPassword) && !TextUtils.isEmpty(userEmail)) {

            if (userPassword.equals(userConfirmPassword)) {
                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            sendToSetUpActivity();
                        } else {

                            String errorMessage = task.getException().getMessage();
                            Toast.makeText(SignUp.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();

                        }

                        progressBar.setVisibility(View.INVISIBLE);

                    }
                });

            } else {

                Toast.makeText(SignUp.this, "Confirm Password and Password Field doesn't match.", Toast.LENGTH_LONG).show();

            }
        } else if (TextUtils.isEmpty(userPassword) || !TextUtils.isEmpty(userConfirmPassword) || TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "insert edit text", Toast.LENGTH_SHORT).show();
        }

    }

    private void sendToSetUpActivity() {
        Intent intent = new Intent(SignUp.this, SetUpActivity.class);
        startActivity(intent);
        finish();
    }
}