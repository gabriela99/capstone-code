package com.example.capstone_code.viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone_code.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Put firebase outside of class - dependency injection
 */
public class RegisterActivity extends AppCompatActivity {
    private EditText etEmailId, etPassword, etName;
    private Button btnRegister;
    private TextView tvSignIn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user !=null){
                Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
                return;
            }
            }
        };

        mAuth = FirebaseAuth.getInstance();

        etName = findViewById(R.id.etName);
        etEmailId = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnRegister = findViewById(R.id.btnRegister);
        tvSignIn = findViewById(R.id.btnSignIn);
    }

    /**
     *
     * @param view
     */
    public void registerUser(View view) {
        final String name = etName.getText().toString();
        final String email = etEmailId.getText().toString();
        final String pwd = etPassword.getText().toString();
        if(name.isEmpty()){
            etName.setError("Please enter your name");
            etName.requestFocus();
        }
        else if(email.isEmpty()){
            etEmailId.setError("Please enter an email");
            etEmailId.requestFocus();
        }
        else if(pwd.isEmpty()){
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
        }
        else if(!(email.isEmpty() && pwd.isEmpty() && name.isEmpty())){
            mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        String userId = mAuth.getCurrentUser().getUid();
                        DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                        Map userInfo = new HashMap<>();
                        userInfo.put("name", name);
//                        userInfo.put("profileImageUrl", "default");
                        userInfo.put("role", "");
                        userInfo.put("skills", "");
                        currentUserDb.updateChildren(userInfo);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this,"SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            Toast.makeText(RegisterActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

        }
        return;
    }

    /**
     *
     * @param view
     */
    public void goToLogin(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        return;
    }

    /**
     *
     */
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    /**
     *
     */
    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}
