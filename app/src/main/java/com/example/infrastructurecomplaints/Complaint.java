package com.example.infrastructurecomplaints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Complaint extends AppCompatActivity {

    private TextView text_subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        text_subject = (TextView)findViewById(R.id.text_subject);
        Intent intent = getIntent();
        String subject = intent.getStringExtra("Subject");
        text_subject.setText(subject);


    }
}
