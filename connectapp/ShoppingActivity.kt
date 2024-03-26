package com.example.connectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.connectapp.adapter.ProductListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingActivity : AppCompatActivity() {
    var userList = arrayListOf<Product>()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

//        getUserList()

        val userprofileList = (arrayListOf<userprofile>())

        userprofileList.add(
            userprofile(
                name = "Jayanth",
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTp8ePHupAwvpCIKWwj8Qzlcg_MC0_CyoWT_Q&usqp=CAU",
                age = "24",
                mail = "suggalajayanth@gmail.com",
                hobbies = "Painting,Cooking,Music",
                lookingfor = "LifePartner",
                describe = getString(R.string.Jayanth)
            )
        )

        userprofileList.add(
            userprofile(
                name = "Chaitanya",
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQAHTDxay4kkSI4D-QtF-wrfgJ665c4d40lXS-XRRoa3jZOjQGfEWVWMjJ3NB2JaWG1HtU&usqp=CAU",
                age = "27",
                mail = "chaitanya007@gmail.com",
                hobbies = "Cinematography,Watching Movies",
                lookingfor = "GirlFriend",
                describe = getString(R.string.Chaitanya)
            )
        )

        userprofileList.add(
            userprofile(
                name = "Akash",
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRuSsJCGev9kaDecwS_JZ_aFO2Y0xwbRUccAw&usqp=CAU",
                age = "26",
                mail = "akash555@gmail.com",
                hobbies = "Playing Cricket,Watching Movies",
                lookingfor = "LifePartner",
                describe = getString(R.string.Akash)
            )
        )

        userprofileList.add(
            userprofile(
                name = "Ganesh",
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyfUXt3l2PiE1hoe9QJ1u7HQ1GxlJfjV5UDw&usqp=CAU",
                age = "25",
                mail = "ganesh111@gmail.com",
                hobbies = "Playing VideoGames,Watching Movies",
                lookingfor = "BestFriend",
                describe = getString(R.string.Ganesh)
            )
        )

        userprofileList.add(
            userprofile(
                name = "Priya",
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsjJf3pfujgEtrnCYYiIL8QcEv2WAhgUKIsw&usqp=CAU",
                age = "23",
                mail = "priyanka2340@gmail.com",
                hobbies = "Playing VideoGames,Watching Movies",
                lookingfor = "BestFriend",
                describe = getString(R.string.Priya)
            )
        )

        userprofileList.add(
            userprofile(
                name = "Koushalya",
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_IFUeZhgqkKLytH7gdBEvdWFoOxsCVRNmdQ&usqp=CAU",
                age = "26",
                mail = "koushalya5999@gmail.com",
                hobbies = "Playing Tennis,Watching TVShows, Music",
                lookingfor = "LifePartner",
                describe = getString(R.string.Kousalya)
            )
        )


        recyclerView = findViewById(R.id.recycler)

        recyclerView.layoutManager = LinearLayoutManager(this)

        //val shoppingAdapter = ShoppingAdapter(userprofileList)
        //recyclerView.adapter = ShoppingListAdapter(userList)


        //shoppingAdapter.onItemClick = {
        //  val intent = Intent(this,FriendRequestActivity::class.java)
        //intent.putExtra("name",it)
        //intent.putExtra("age",it)
        //intent.putExtra("mail",it)
        //intent.putExtra("hobbies",it)
        //intent.putExtra("lookingfor",it)
        //intent.putExtra("describe",it)
        //intent.putExtra("image",it)
        //startActivity(intent)
        //}
        val searchButton = findViewById<Button>(R.id.searchButton)
        val searchEditText = findViewById<EditText>(R.id.searchEditText)

        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            if (query.isNotEmpty()) {
                getUserList(query)
                Log.d("Sample", getUserList(query).toString())
            } else {
                Toast.makeText(this, "Please enter a search term", Toast.LENGTH_SHORT).show()
            }
        }
    }

        fun getUserList(query: String) {
            var retrofit = RetrofitClient.getInstance()
            var apiInterface = retrofit.create(ApiInterface::class.java)
            print(retrofit.baseUrl())
            lifecycleScope.launchWhenCreated {
                try {
                    val response = apiInterface.searchProducts(query = query)
                    if (response.isSuccessful()) {
                        val data: ProductResponse = response.body() as ProductResponse
                        userList = data.products
                       
                        withContext(Dispatchers.Main) {
                            recyclerView.adapter = ProductListAdapter(userList)
                        }
                    } else {
                        Toast.makeText(
                            this@ShoppingActivity,
                            response.errorBody().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (Ex: Exception) {
                    Log.e("Error", Ex.localizedMessage)
                }
            }


        }
    }





