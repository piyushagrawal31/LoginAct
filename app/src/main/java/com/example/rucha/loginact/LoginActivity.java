package com.example.rucha.loginact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView lcltxtUserName;
    private TextView lcltxtPassword;
    private Button lclbtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lcltxtUserName = findViewById(R.id.txtUserName);
        lcltxtPassword = findViewById(R.id.txtPassword);

        lclbtnLogin = findViewById(R.id.btnLogin);
        lclbtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == lclbtnLogin){

            String valtxtUserName = lcltxtUserName.getText().toString();
            String valtxtPassword = lcltxtPassword.getText().toString();

            Intent dataIntent = new Intent(LoginActivity.this, WelcomeActivity.class);

            if (!valtxtUserName.isEmpty()){
                dataIntent.putExtra(WelcomeActivity.welUserName, valtxtUserName);
                dataIntent.putExtra(WelcomeActivity.welPassword, valtxtPassword);
                startActivity(dataIntent);}
            else {
                Toast.makeText(getApplicationContext(), "Username is mandatory!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

    }
}
