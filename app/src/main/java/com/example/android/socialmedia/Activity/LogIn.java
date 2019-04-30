package com.example.android.socialmedia.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.socialmedia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    Button logIn;
    TextView createAccount;
    EditText email, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        logIn = findViewById(R.id.so7ba_login_button);
        createAccount = findViewById(R.id.crate_account_text);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.progress_bar_logIn);
        mAuth = FirebaseAuth.getInstance();

        logIn.setOnClickListener(this);
        createAccount.setOnClickListener(this);
    }

    @Override
    public void onClick( View view ) {
        switch (view.getId()) {
            case R.id.so7ba_login_button:
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                if (TextUtils.isEmpty(emailText) || TextUtils.isEmpty(passwordText)) {
                    Toast.makeText(this, "you should insert you email and password", Toast.LENGTH_LONG).show();
                } else if (!TextUtils.isEmpty(emailText) && !TextUtils.isEmpty(passwordText)) {
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete( @NonNull Task<AuthResult> task ) {
                            if (task.isSuccessful()) {
                                sendToHome();
                            } else {
                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(LogIn.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();
                            }
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                }
                break;
            case R.id.crate_account_text:
                startActivity(new Intent(LogIn.this, SignUp.class));
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            sendToHome();
        }
    }

    private void sendToHome() {
        Intent homeIntent = new Intent(LogIn.this, Home.class);
        startActivity(homeIntent);
        finish();

    }

}
