package com.example.infrastructurecomplaints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Feedback extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText text_feedback;
    private String docId;
    private String user;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        db = FirebaseFirestore.getInstance();
        text_feedback = (EditText)findViewById(R.id.text_feedback);


        intent = getIntent();
        docId = intent.getStringExtra("ComplaintId");

        SharedPrefrencesConfig sharedPrefrencesConfig = new SharedPrefrencesConfig(getApplicationContext());

        user = sharedPrefrencesConfig.readUserInfo();
    }

    public void submitFeedback(View view) {

        //Creating data
        Map<String,Object> data = new HashMap<>();
        data.put("User",user);
        data.put("Complaint_Id",docId);
        data.put("Feedback",text_feedback.getText().toString());

        db.collection("feedbacks").add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(Feedback.this, "Feedback saved", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(Feedback.this, "Something wrong happened", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
