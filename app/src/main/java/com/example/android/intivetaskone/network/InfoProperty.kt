package com.example.android.intivetaskone.network

import com.squareup.moshi.Json

data class InfoProperty(
    val title : String,
    val desc : String,

    @Json(name = "url")
    val url : String
)