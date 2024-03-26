package com.example.connectapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private val validUsername = "JayanthSuggala"
    private val validPassword = "Jayanth@123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_login)


        val usernameEditText = findViewById<EditText>(R.id.un)
        val passwordEditText = findViewById<EditText>(R.id.pw)
        val loginButton = findViewById<Button>(R.id.btn1)
        val signUpButton = findViewById<Button>(R.id.btn2)
        val forgetpasswordTextView = findViewById<TextView>(R.id.fp)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username == validUsername && password == validPassword) {
                // Username and password match, navigate to home page
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {

                Toast.makeText(this, "Your UserName and Password Doesn't Match", Toast.LENGTH_SHORT).show()
            }
        }

        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        forgetpasswordTextView.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

    }
}











