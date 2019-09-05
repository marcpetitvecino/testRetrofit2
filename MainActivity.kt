package com.example.testretrofit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testretrofit.Interface.JsonPlaceHolderApi
import com.example.testretrofit.Model.Posts
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mJsonTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mJsonTextView = findViewById(R.id.jsontext)
        getPosts()
    }

    private fun getPosts() {

        val retrofit =  Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        var call: Call<List<Posts>> = jsonPlaceHolderApi.getPosts()

        call.enqueue(object: Callback<List<Posts>> {

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {

                if (!response.isSuccessful()) {

                    mJsonTextView.setText("Codigo: " + response.code())
                    return

                }

                var postsList: List<Posts>? = response.body()

                if (postsList != null) {
                    for(post in postsList) {

                        var content: String = ""
                        content += "userId: " + post.getUserId() + "\n"
                        content += "id: " + post.getId() + "\n"
                        content += "title: " + post.getTitle() + "\n"
                        content += "body: " + post.getBody() + "\n\n"
                        mJsonTextView.append(content)

                    }


                }
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {

                mJsonTextView.setText(t.message)

            }

        })
    }

}
