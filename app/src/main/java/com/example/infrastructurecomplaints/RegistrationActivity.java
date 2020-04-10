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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void signup(View view) {
        EditText text_name = (EditText) findViewById(R.id.text_name);
        EditText text_email = (EditText) findViewById(R.id.text_email);
        EditText text_password = (EditText) findViewById(R.id.text_newpassword);
        EditText text_dob = (EditText) findViewById(R.id.text_dob);
        EditText text_contact = (EditText) findViewById(R.id.text_contact);
        String name = text_name.getText().toString();
        String email = text_email.getText().toString();
        String password = text_password.getText().toString();
        String dob = text_dob.getText().toString();
        String contact = text_contact.getText().toString();

        if (text_name.getText().toString().isEmpty() || text_name.getText().toString().length() > 32) {
            text_name.setError("Enter valid name:max length 32");
            text_name.requestFocus();
        } else if (text_email.getText().toString().length() == 0) {
            text_email.setError("Enter valid email");
            text_email.requestFocus();

        } else if (text_password.getText().toString().length() <= 4) {
            text_password.setError("Enter valid password:min length 5");
            text_password.requestFocus();

        } else if (text_dob.getText().toString().isEmpty()) {
            text_dob.setError("Enter valid DOB");
            text_dob.requestFocus();
        } else if (text_contact.getText().toString().length() != 10) {
            text_contact.setError("Enter 10 digit number");
            text_contact.requestFocus();
        } else {
            Map<String, Object> user = new HashMap<>();
            user.put("Name", name);
            user.put("Password", password);
            user.put("Contact_No", contact);
            user.put("Birth_Date", dob);
            user.put("Blocked","False");
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("users").document(email).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegistrationActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Something Went worng", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
