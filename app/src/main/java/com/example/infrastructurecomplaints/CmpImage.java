package com.example.infrastructurecomplaints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import io.grpc.Context;

public class CmpImage extends AppCompatActivity {

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmp_image);

        imageView = findViewById(R.id.imageView_show);

        storage = FirebaseStorage.getInstance();

        Intent intent = getIntent();

        String URL = intent.getStringExtra("ComplaintId") + ".jpg";




        storageReference = storage.getReferenceFromUrl("gs://nitc-infrastructure-complaints.appspot.com/images");
        StorageReference imageReference = storageReference.child(URL);

        imageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()) {
                    Picasso.with(getApplicationContext()).load(task.getResult()).into(imageView);
                }
            }
        });





    }
}
