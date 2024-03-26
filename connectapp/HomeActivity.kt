package com.example.connectapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import android.widget.Button
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import android.content.SharedPreferences
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner


class HomeActivity : AppCompatActivity() {
    private lateinit var interestsTextView: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private var isLoggedIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val commentEditText = findViewById<EditText>(R.id.commment)
        val submitButton = findViewById<Button>(R.id.btn1)
        val logoutButton = findViewById<Button>(R.id.btn2)


        submitButton.setOnClickListener {

            val comment = commentEditText.text.toString()
            Toast.makeText(this, "Your comments are received: $comment", Toast.LENGTH_SHORT).show()
        }

        logoutButton.setOnClickListener {
            logout()
        }

        sharedPreferences = getSharedPreferences("SignUpDetails", MODE_PRIVATE)

        interestsTextView = findViewById(R.id.interests)

        val username = sharedPreferences.getString("username", "")
        val gender = sharedPreferences.getString("gender", "")
        val selectedCheckboxes = sharedPreferences.getStringSet("selectedCheckboxes", setOf())

        if (username != null && gender != null && selectedCheckboxes != null) {
            val greeting = if (gender.equals(
                    "Male",
                    ignoreCase = true
                )
            ) "Hey buddy, Welcome to Connect" else "Hey madam, Welcome to Connect"
            val displayText = "$greeting $username\n"

            val selectedInterests = selectedCheckboxes.joinToString("\n")
            val finalText = "$displayText\nYour Interests Are:\n$selectedInterests"

            interestsTextView.text = finalText
        }

        val dynamicItems =
            arrayOf("Select To Open", "Open Messenger", "Open My Profile", "Change Password","Friend Suggestions")



        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dynamicItems)


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        val dynamic_spinnerSpinner = findViewById<Spinner>(R.id.dynamic_spinner)
        dynamic_spinnerSpinner.adapter = adapter

        val staticItems = arrayOf("Select Your Day","Beautiful Day", "Boring Day", "Depressing Day")
        val static_spinnerSpinner = findViewById<Spinner>(R.id.static_spinner)


        val adapterStatic = ArrayAdapter(this, android.R.layout.simple_spinner_item, staticItems)


        adapterStatic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        static_spinnerSpinner.adapter = adapterStatic



        static_spinnerSpinner.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?
                ,view: View?
                ,position: Int
                ,id: Long){
                val selectedItem = parent?.getItemAtPosition(position).toString()
                when (selectedItem) {
                    "Beautiful Day" -> handleBeautifulDay()
                    "Boring Day" -> handleBoringDay()
                    "Depressing Day" -> handleDepressingDay()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        })
    }

       override fun onResume() {
            super.onResume()

           val dynamic_spinnerSpinner = findViewById<Spinner>(R.id.dynamic_spinner)

            dynamic_spinnerSpinner.setOnItemSelectedListener(object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?,view: View?, position: Int, id: Long) {
                    val selectedItem = parent?.getItemAtPosition(position).toString()


                    when (selectedItem) {
                        "Open Messenger" -> navigateToActivity(MessageActivity::class.java)
                        "Open My Profile" -> navigateToActivity(ProfileActivity::class.java)
                        "Change Password" -> navigateToActivity(ForgetPasswordActivity::class.java)
                        "Friend Suggestions" -> navigateToActivity(ShoppingActivity::class.java)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            })
        }

    override fun onBackPressed() {
            if (isLoggedIn) {
                super.onBackPressed()
            }
            else {
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        }


    private fun logout() {
        isLoggedIn = false
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }


    private fun navigateToActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
                private fun handleBeautifulDay() {
            Toast.makeText(this, "Hurray! Iam Happy For Your Day", Toast.LENGTH_SHORT).show()

        }

                private fun handleBoringDay() {
            Toast.makeText(this, "Don't Worry! You Can Have Fun Here", Toast.LENGTH_SHORT).show()

        }

                private fun handleDepressingDay() {
            Toast.makeText(this, "No Worries! Connect Will Make Your Day Happy", Toast.LENGTH_SHORT).show()

        }


}