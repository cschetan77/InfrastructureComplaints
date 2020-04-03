package com.example.infrastructurecomplaints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        EditText text_email = (EditText) findViewById(R.id.text_email);
        String email = text_email.getText().toString();
        EditText text_password = (EditText) findViewById(R.id.text_newpassword);
        final String password = text_password.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(email).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()) {
                        //Record Exist and check for password
                        Toast.makeText(MainActivity.this,"User found",Toast.LENGTH_SHORT).show();
                        if(password.equals(document.get("Password"))) {
                            //Correct password start Main Application
                            Toast.makeText(MainActivity.this,"User found and password matched",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,ListComplaints.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Wrong password",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        //Record don't exist
                        Toast.makeText(MainActivity.this,"User not found",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    //Query not Completed Guess network problem
                    Toast.makeText(MainActivity.this,"Query not completed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signup(View view) {
        Intent intent = new Intent(this,RegistrationActivity.class);
        startActivity(intent);
    }
}
