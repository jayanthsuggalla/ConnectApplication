package com.example.connectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

@Suppress("DEPRECATION")
class FriendRequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_request)


        val userProfile = intent.getParcelableExtra<userprofile>("userProfile")
        if (userProfile != null) {
            val textView = findViewById<TextView>(R.id.tvdetails)
            val imageView = findViewById<ImageView>(R.id.propic)

            textView.text = userProfile.name
            textView.text = userProfile.age
            textView.text = userProfile.mail
            textView.text = userProfile.hobbies
            textView.text = userProfile.lookingfor
            textView.text = userProfile.describe
            imageView.setImageResource(userProfile.image)
        }
    }
}

private fun ImageView.setImageResource(image: String) {
    Picasso.get()
        .load(image)
        .placeholder(R.mipmap.ic_launcher)
        .error(R.drawable.cl1)
        .into(this)
}
