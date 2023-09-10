package com.example.mirrarrassigment.repository

import androidx.lifecycle.MutableLiveData
import com.example.mirrarrassigment.response.NasaResponse
import com.example.mirrarrassigment.service.AuthenticationService
import com.example.mirrarrassigment.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(val authService: AuthenticationService) {

    fun getImageOfNasa(api_key: String): MutableLiveData<Resource<NasaResponse>> {
        val mutableLiveData: MutableLiveData<Resource<NasaResponse>> = MutableLiveData()
        authService.getImageOfNasa(api_key).enqueue(object : Callback<NasaResponse?> {
            override fun onResponse(
                call: Call<NasaResponse?>,
                response: Response<NasaResponse?>
            ) {
                if (response.body() != null && response.isSuccessful && response.code() == 200) {
                    mutableLiveData.postValue(Resource.Success(response.body()!!))
                } else {
                    mutableLiveData.postValue(Resource.Error(response.message()))
                }
            }

            override fun onFailure(call: Call<NasaResponse?>, t: Throwable) {
                mutableLiveData.postValue(Resource.Error(t.message.toString()))
            }
        })


        return mutableLiveData
    }


}