package com.example.connectapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView

class ProfileActivity : AppCompatActivity() {

    private lateinit var profileShapeableImageView: ShapeableImageView
    private lateinit var floatFloatingActionButton: FloatingActionButton

    companion object {
        const val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        floatFloatingActionButton = findViewById(R.id.cameraaccess)
        profileShapeableImageView = findViewById(R.id.profilepicture)

        floatFloatingActionButton.setOnClickListener {
            pickImageGallery()
        }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
   //     if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
     //       profileShapeableImageView.setImageURI(data?.data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                profileShapeableImageView.setImageURI(uri)
            }

        }
    }
}
