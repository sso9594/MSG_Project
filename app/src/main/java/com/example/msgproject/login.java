package com.example.msgproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    private Button loginButton;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginButton = findViewById(R.id.btn_Login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the login logic here

                // Start MainActivity and navigate to Main_frag
                Intent intent = new Intent(login.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: Close the login activity
            }
        });

        signUpButton = findViewById(R.id.btn_Signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the button click event
                // Navigate to the signup activity or perform desired action

                // Example: Start SignUpActivity
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });
    }
}
