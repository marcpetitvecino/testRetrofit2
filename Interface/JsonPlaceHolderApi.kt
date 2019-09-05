package com.example.testretrofit.Interface

import com.example.testretrofit.Model.Posts
import retrofit2.Call
import retrofit2.http.GET

public interface JsonPlaceHolderApi {


    @GET("posts")
    fun getPosts(): Call<List<Posts>>


}