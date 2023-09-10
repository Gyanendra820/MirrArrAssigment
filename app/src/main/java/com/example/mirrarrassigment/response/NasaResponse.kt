package com.example.mirrarrassigment.response

data class NasaResponse(
    val success: Boolean = true,
    val date: String,
    val explanation: String,
    var media_type: String = "video",
    val title: String,
    val url: String
)