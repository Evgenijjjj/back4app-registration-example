package com.example.admin.registrationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    TextView registerBtn;
    EditText editName, editPassword;
    Button LoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        registerBtn = (TextView)findViewById(R.id.loginGoToRegister);
        editName = (EditText) findViewById(R.id.loginEditName);
        editPassword = (EditText) findViewById(R.id.loginEditPassword);
        LoginBtn = (Button) findViewById(R.id.loginBtnLogin);

        registerBtn.setOnClickListener(this);
        LoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginGoToRegister :
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;

            case R.id.loginBtnLogin :
                if(editName.getText().toString() == "" || editPassword.getText().toString() == ""){
                    Toast.makeText(LoginActivity.this, "Enter Correct Data", Toast.LENGTH_LONG).show();
                    return;
                }

                ParseUser.logInInBackground(editName.getText().toString(), editPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null){
                            Toast.makeText(LoginActivity.this, "You Are Successfully Login", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, LogoutActivity.class));
                        }else{
                            ParseUser.logOut();
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

        }
    }
}
