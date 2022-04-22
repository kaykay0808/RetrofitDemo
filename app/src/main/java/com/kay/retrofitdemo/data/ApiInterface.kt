package com.kay.retrofitdemo.data

import retrofit2.Call
import retrofit2.http.GET

// declare our get request

interface ApiInterface {

    // (1) create a get request (make sure to get the retrofit 'Call')
    @GET("posts"/* <- Adding the endpoint of the URL*/)
    fun getData(): Call<List<MyDataItem>>
}