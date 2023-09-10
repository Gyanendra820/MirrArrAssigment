package com.example.mirrarrassigment.utils

import okhttp3.ResponseBody
import java.io.Reader

sealed class Resource<T>(
        open val data: T? = null,
        val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
   // class Error<T>(message: ResponseBody?, data: T? = null) : Resource<T>(data, message)
    class Error<T>( message:String?,data: T? = null) : Resource<T>(data,message)


    class Loading<T> : Resource<T>()
}