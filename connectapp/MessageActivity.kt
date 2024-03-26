package com.example.connectapp


import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView

class MessageActivity : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        val searchEditText = findViewById<EditText>(R.id.ed1)
        val searchoneEditText = findViewById<EditText>(R.id.ed2)
        val callButton = findViewById<Button>(R.id.call)
        val messageButton = findViewById<Button>(R.id.message)
        val searchButton = findViewById<Button>(R.id.search)
        val camButton = findViewById<Button>(R.id.camone)
        val mapButton = findViewById<Button>(R.id.maps)
        val musicButton = findViewById<Button>(R.id.music)
        val emailButton = findViewById<Button>(R.id.email)
        val settingButton = findViewById<Button>(R.id.setting)
        val autoComplete = findViewById<AutoCompleteTextView>(R.id.autocompletetextview)




        callButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${searchEditText.text}"))
            startActivity(intent)
        }

        searchButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, searchoneEditText.text.toString())
            startActivity(intent)
        }

        messageButton.setOnClickListener{

            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("sms:${searchEditText.text}"))
            intent.putExtra("address", "555-1234");
            intent.putExtra("sms_body", "Enter your message here");
            startActivity(intent)
        }

        camButton.setOnClickListener{

            val myIntent = Intent()
            myIntent.setType("image/pictures/*")
            myIntent.setAction(Intent.ACTION_GET_CONTENT)
            startActivity(myIntent)

        }

        mapButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:${autoComplete.text}"))
            startActivity(intent)
        }

        musicButton.setOnClickListener{}
        val intent = Intent("android.intent.action.MUSIC_PLAYER")
        startActivity(intent)

        emailButton.setOnClickListener{
            val uri = Uri.parse("mailto:${searchoneEditText.text}")
            val intent= Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Add Subject")
            intent.putExtra(Intent.EXTRA_TEXT, "Enter your Mail")
            startActivity(intent)
        }

        settingButton.setOnClickListener{
            val intent = Intent(Settings.ACTION_SETTINGS)
            startActivity(intent)
        }


        val countries = listOf("HYDERABAD", "INDIA", "TELANGANA", "KHAMMAM", "WARANGAL")

        val autoCompleteAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, countries)
        autoComplete.setAdapter(autoCompleteAdapter)


        val listView = findViewById<ListView>(R.id.listview)
        val contacts = listOf("Contact 1", "Contact 2", "Contact 3")
        val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contacts)
        listView.adapter = listAdapter

    }
}