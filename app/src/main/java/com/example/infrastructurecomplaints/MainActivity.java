package com.example.infrastructurecomplaints;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText text_email,text_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        text_email = (EditText)findViewById(R.id.text_email);
        String email = text_email.getText().toString();
        text_password = (EditText)findViewById(R.id.text_password);
        String password = text_password.getText().toString();
        
    }
}
