package com.example.admin.registrationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText edPas1, edPas2, edName;
    private Button RegisterBtn;
    private TextView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        edPas1 = (EditText)findViewById(R.id.regEditPassword1);
        edPas2 = (EditText)findViewById(R.id.regEditPassword2);
        edName = (EditText)findViewById(R.id.regEditName);
        RegisterBtn = (Button)findViewById(R.id.regBtnRegister);
        backBtn = (TextView) findViewById(R.id.regGoBackBtn);

        RegisterBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.regBtnRegister){
            if(edName.getText().toString() != "" && edPas2.getText().toString() != "" &&
                    (edPas2.getText().toString() == edPas1.getText().toString())){
                Toast.makeText(RegisterActivity.this, "Enter Correct Data", Toast.LENGTH_LONG).show();
                return;
            }
            ParseUser user = new ParseUser();

            user.setUsername(edName.getText().toString());
            user.setPassword(edPas1.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(RegisterActivity.this, "Successful Create Account!\nWelcome\n" + edName.getText().toString(), Toast.LENGTH_LONG).show();
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }

        if(v.getId() == R.id.regGoBackBtn){
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }
    }
}
