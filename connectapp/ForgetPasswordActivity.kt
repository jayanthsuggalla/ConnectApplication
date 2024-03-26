package com.example.connectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ForgetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        val passwordEditText = findViewById<EditText>(R.id.ed2)
        val confirmPasswordEditText = findViewById<EditText>(R.id.ed3)
        val submitButton = findViewById<Button>(R.id.btn)

        submitButton.setOnClickListener {
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (password == confirmPassword) {

                Toast.makeText(this, "You changed your password successfully", Toast.LENGTH_SHORT).show()

            } else {

                Toast.makeText(this, "Your Password and ConfirmPassword doesn't match", Toast.LENGTH_SHORT).show()
            }
        }
    }
}