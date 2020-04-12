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
    private SharedPrefrencesConfig prefrencesConfig;
    EditText text_email;
    EditText text_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefrencesConfig = new SharedPrefrencesConfig(getApplicationContext());
        text_email = (EditText) findViewById(R.id.text_email);
        text_password = (EditText) findViewById(R.id.text_newpassword);

        if(prefrencesConfig.readLoginStatus()) {
                startActivity(new Intent(this,ListComplaints.class));
                finish();
        }
    }

    public void login(View view) {

        //Getting TextFields
        final String email = text_email.getText().toString();
        final String password = text_password.getText().toString();

        //Querying from database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(email).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()) {
                        //Record Exist and check for password

                        if(document.get("Blocked").equals("True")) {
                            Toast.makeText(MainActivity.this, "User Blocked", Toast.LENGTH_SHORT).show();
                            text_email.setText("");
                            text_password.setText("");
                        }
                        else if(password.equals(document.get("Password"))) {
                            //Correct password start Main Application
                            Toast.makeText(MainActivity.this,"User found and password matched",Toast.LENGTH_SHORT).show();

                            //Setting shared prefrences
                            prefrencesConfig.writeLoginStatus(true);
                            prefrencesConfig.writeUserInfo(email);

                            Intent intent = new Intent(MainActivity.this,ListComplaints.class);
                            intent = intent.putExtra("Email", email);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Wrong password",Toast.LENGTH_SHORT).show();

                            text_password.setText("");
                        }
                    }
                    else {
                        //Record don't exist
                        Toast.makeText(MainActivity.this,"User not found",Toast.LENGTH_SHORT).show();
                        text_email.setText("");
                        text_password.setText("");
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
