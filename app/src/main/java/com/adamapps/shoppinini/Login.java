package com.adamapps.shoppinini;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText emailField,passField;
    private Button loginButton;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#fff"));
        toolbar.showOverflowMenu();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("Loging In");
        progressDialog.setMessage("Sit Back And Relax :-)");

        emailField = (EditText) findViewById(R.id.email_login);
        passField = (EditText) findViewById(R.id.pass_login);
        loginButton = (Button) findViewById(R.id.loginButton);

    }

    public void LoginButton(View v){

        String email = emailField.getText().toString().trim();
        String password = passField.getText().toString().trim();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(this, "Enter All Fields To Proceed", Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(500).playOn(loginButton);
            return;
        }

        progressDialog.show();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(Login.this,Home.class));
                        progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, ""+e, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }

}
