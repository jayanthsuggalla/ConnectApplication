package com.example.connectapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.connectapp.FriendRequestActivity
import com.example.connectapp.R
import com.example.connectapp.userprofile
import com.squareup.picasso.Picasso

class ShoppingAdapter(private val userprofileList: ArrayList<userprofile>):
    RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {

    var onItemClick: ((userprofile) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shopping_adapter, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userprofileList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userProfile = userprofileList[position]
        holder.bind(userProfile)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: AppCompatTextView = view.findViewById(R.id.name)
        private val age: AppCompatTextView = view.findViewById(R.id.age)
        private val mail: AppCompatTextView = view.findViewById(R.id.mailone)
        private val hobbie: AppCompatTextView = view.findViewById(R.id.hobbie)
        private val lookingfor: AppCompatTextView = view.findViewById(R.id.lookingfor)
        private val image: AppCompatImageView = view.findViewById(R.id.profileimage)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val userProfile = userprofileList[position]
                    val context = itemView.context
                    val intent = Intent(context, FriendRequestActivity::class.java)
                    intent.putExtra("userProfile", userProfile)
                    context.startActivity(intent)
                }
            }
        }

        fun bind(userProfile: userprofile) {
            name.text = userProfile.name
            age.text = userProfile.age
            mail.text = userProfile.mail
            hobbie.text = userProfile.hobbies
            lookingfor.text = userProfile.lookingfor
            Picasso.get()
                .load(userProfile.image)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.cl1)
                .into(image)
        }
    }
}
