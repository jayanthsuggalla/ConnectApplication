package com.example.connectapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.connectapp.FriendRequestActivity
import com.example.connectapp.Product
import com.example.connectapp.R
import com.example.connectapp.userprofile
import com.squareup.picasso.Picasso

class ProductListAdapter(private val userprofileList: ArrayList<Product>):
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    var onItemClick: ((userprofile) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shopping_adapter, parent, false)

        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (userprofileList[position].title.isNotEmpty()){
            1
        }
        else{
            0
        }
    }

    override fun getItemCount(): Int {
        return userprofileList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userProfile = userprofileList[position]
        holder.bind(userProfile)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: AppCompatTextView = view.findViewById(R.id.name)
        private val description: AppCompatTextView = view.findViewById(R.id.mailone)
        private val price: AppCompatTextView = view.findViewById(R.id.hobbie)
        private val thumbnail: AppCompatTextView = view.findViewById(R.id.lookingfor)
        private val images: AppCompatImageView = view.findViewById(R.id.profileimage)

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

        fun bind(userProfile: Product) {
            title.text = userProfile.title
            description.text = userProfile.description
            price.text = userProfile.price.toString()
            thumbnail.text = userProfile.thumbnail
            Picasso.get()
                .load(userProfile.images[0])
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.cl1)
                .into(images)
        }
    }
}

private fun Intent.putExtra(s: String, userProfile: Product) {

    TODO("Not yet implemented")
}
