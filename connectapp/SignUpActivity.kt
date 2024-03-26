package com.example.connectapp


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.CheckBox
import android.widget.Toast
import android.content.Intent
import android.widget.EditText
import android.widget.RadioButton
import android.content.SharedPreferences
import android.net.Uri
import android.view.View
import androidx.constraintlayout.widget.Group

class SignUpActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_sign_up)

        sharedPreferences = getSharedPreferences("SignUpDetails", Context.MODE_PRIVATE)

        usernameEditText = findViewById(R.id.username)
        val rgRadioGroup = findViewById<RadioGroup>(R.id.radiogroup)
        val cboneCheckBox = findViewById<CheckBox>(R.id.cb1)
        val cbtwoCheckBox = findViewById<CheckBox>(R.id.cb2)
        val cbthreeCheckBox = findViewById<CheckBox>(R.id.cb3)
        val cbfourCheckBox = findViewById<CheckBox>(R.id.cb4)
        val cbfiveCheckBox = findViewById<CheckBox>(R.id.cb5)
        val cbsixCheckBox = findViewById<CheckBox>(R.id.cb6)
        val signgroupGroup = findViewById<Group>(R.id.Signgroup)
        val adultCheckBox = findViewById<CheckBox>(R.id.adult)
        val sendotpButton = findViewById<Button>(R.id.sendotp)
        val fathermailEditText = findViewById<EditText>(R.id.fathermail)
        val fathernameEditText = findViewById<EditText>(R.id.fathername)
        val fatherphnEditText = findViewById<EditText>(R.id.fatherphn)
        val registerButton = findViewById<Button>(R.id.register)

        adultCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                signgroupGroup.visibility = View.VISIBLE
            } else {
                signgroupGroup.visibility = View.GONE
            }
        }

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val gender =
                findViewById<RadioButton>(rgRadioGroup.checkedRadioButtonId).text.toString()
            val selectedCheckboxes = mutableListOf<String>()

            if (cboneCheckBox.isChecked) selectedCheckboxes.add(cboneCheckBox.text.toString())
            if (cbtwoCheckBox.isChecked) selectedCheckboxes.add(cbtwoCheckBox.text.toString())
            if (cbthreeCheckBox.isChecked) selectedCheckboxes.add(cbthreeCheckBox.text.toString())
            if (cbfourCheckBox.isChecked) selectedCheckboxes.add(cbfourCheckBox.text.toString())
            if (cbfiveCheckBox.isChecked) selectedCheckboxes.add(cbfiveCheckBox.text.toString())
            if (cbsixCheckBox.isChecked) selectedCheckboxes.add(cbsixCheckBox.text.toString())

            saveSignUpDetails(username, gender, selectedCheckboxes)

            // Navigate to login screen or wherever appropriate
            // For demonstration purpose, let's assume we are moving to login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }


    }

    private fun saveSignUpDetails(
        username: String,
        gender: String,
        selectedCheckboxes: List<String>
    ) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("gender", gender)
        editor.putStringSet("selectedCheckboxes", selectedCheckboxes.toSet())
        editor.apply()
    }


}

