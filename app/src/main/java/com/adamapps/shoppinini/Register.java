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

public class Register extends AppCompatActivity {

    private EditText emailField,passField;
    private Button registerButton;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#fff"));
        toolbar.showOverflowMenu();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("Registering...");
        progressDialog.setMessage("Setting Up Account");

        emailField = (EditText) findViewById(R.id.email_register);
        passField = (EditText) findViewById(R.id.pass_register);
        registerButton = (Button) findViewById(R.id.registerButton);

    }

    public void RegisterButton(View v){

        String email = emailField.getText().toString().trim();
        String password = passField.getText().toString().trim();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(this, "Enter All Fields To Proceed", Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(500).playOn(registerButton);
            return;
        }

        progressDialog.show();
        YoYo.with(Techniques.RubberBand).duration(500).playOn(registerButton);
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(Register.this,Home.class));
                        progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(Register.this, ""+e, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
