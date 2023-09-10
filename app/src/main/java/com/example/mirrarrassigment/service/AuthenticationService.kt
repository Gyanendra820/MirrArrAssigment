package com.example.mirrarrassigment.service

import com.example.mirrarrassigment.response.NasaResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthenticationService {

    @GET("planetary/apod?")
    fun getImageOfNasa(@Query("api_key") apiKey: String): Call<NasaResponse>
}