package com.example.mirrarrassigment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mirrarrassigment.repository.AuthenticationRepository
import com.example.mirrarrassigment.response.NasaResponse
import com.example.mirrarrassigment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NasaViewModel @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
    ViewModel() {
    fun getImageOfNasa(api_key: String): MutableLiveData<Resource<NasaResponse>> {
        return authenticationRepository.getImageOfNasa(api_key)
    }
}