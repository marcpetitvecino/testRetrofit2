package com.example.testretrofit.Model

public class Posts {

    private var userId: Int =  0
    private var id: Int = 0
    private var title: String = ""
    private var body: String = ""

    fun getUserId(): Int {

        return userId

    }

    fun getId(): Int {

        return id

    }

    fun getTitle(): String {

        return title

    }

    fun getBody(): String {

        return body

    }



}