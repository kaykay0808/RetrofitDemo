package com.kay.retrofitdemo.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// write the data object the same as the output jSon file if not using the SerialName

@Serializable
data class MyDataItem(
    @SerialName("body")
    val body: String,
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("userId")
    val userId: Int
)