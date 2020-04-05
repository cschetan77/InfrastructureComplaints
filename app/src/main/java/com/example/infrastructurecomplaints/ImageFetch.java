package com.example.infrastructurecomplaints;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ImageFetch extends AppCompatActivity {

    private FirebaseStorage stotage;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_fetch);

        //Getting instance of Stotage
        stotage = FirebaseStorage.getInstance();

    }
}
