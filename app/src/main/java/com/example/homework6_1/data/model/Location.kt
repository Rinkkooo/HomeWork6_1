package com.example.homework6_1.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) : Parcelable