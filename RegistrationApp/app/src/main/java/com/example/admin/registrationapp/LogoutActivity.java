package com.example.admin.registrationapp;

import android.content.Intent;
import android.os.UserManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseUser;

public class LogoutActivity extends AppCompatActivity implements View.OnClickListener{

    Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        logoutBtn = (Button)findViewById(R.id.logoutBtn);

        logoutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.logoutBtn){
            Toast.makeText(LogoutActivity.this, "You are Log Out !", Toast.LENGTH_LONG).show();
            ParseUser.logOut();
            startActivity(new Intent(LogoutActivity.this, LoginActivity.class));
        }
    }
}
