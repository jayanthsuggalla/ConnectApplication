package com.example.connectapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import android.widget.Button
import android.content.Intent

class OtpVerificationActivity : AppCompatActivity() {

    private lateinit var email: String
    private lateinit var otp: String
    private lateinit var otpEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)

        email = intent.getStringExtra("email") ?: ""
        otp = intent.getStringExtra("otp") ?: ""

       val  otpEditText = findViewById<EditText>(R.id.enterotp)
        val verifyButton = findViewById<Button>(R.id.verify)

        verifyButton.setOnClickListener {
            val enteredOTP = otpEditText.text.toString().trim()

            if (enteredOTP == otp) {

                Toast.makeText(this, "OTP Verified", Toast.LENGTH_SHORT).show()

                 startActivity(Intent(this, SignUpActivity::class.java))
                 finish()
            } else {
                // Incorrect OTP entered, show error message
                Toast.makeText(this, "Incorrect OTP", Toast.LENGTH_SHORT).show()
            }
        }

    }
}