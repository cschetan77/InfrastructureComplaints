package com.example.infrastructurecomplaints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CreateComplaint extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText sub,des;
    String email;
    private Map<String,Object> data = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint);
        sub = (EditText)findViewById(R.id.text_subject_cc);
        des = (EditText)findViewById(R.id.text_description_cc);

        Intent intent = getIntent();
        email = intent.getStringExtra("Email");
        //Getting databse instance
        db = FirebaseFirestore.getInstance();


    }

    public void create(View view) {


        data.put("User",email);
        data.put("Subject",sub.getText().toString());
        data.put("Description",des.getText().toString());
        Toast.makeText(this, sub.getText().toString(), Toast.LENGTH_SHORT).show();
        data.put("Feedback","Not Specified");
        data.put("Image","url://");
        data.put("Priority","Not Specified");
        data.put("Status","Pending");
        data.put("Type","Not Specified");

        //Getting current date and time
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");
        String date = simpleDateFormat.format(calendar.getTime());
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm:ss");
        String time = simpleTimeFormat.format(calendar.getTime());

        data.put("Time",time);
        data.put("Date",date);


        db.collection("complaints").add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(CreateComplaint.this, "Complaint Lodged successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateComplaint.this,ListComplaints.class);

                    //Get user  from shared prefrences config
                    //intent.putExtra("Email",email);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(CreateComplaint.this, "Something gone wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void clickImage(View view) {
        if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
        }
    }
}
