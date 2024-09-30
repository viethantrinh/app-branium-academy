package com.example.braniumacadamy.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.braniumacadamy.data.model.auth.Result
import com.example.braniumacadamy.data.model.auth.SignInData
import com.example.braniumacadamy.data.model.auth.SignUpData
import com.example.braniumacadamy.data.repository.auth.UserRepository
import com.example.braniumacadamy.data.resource.ResultResponse
import kotlinx.coroutines.launch


class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _token = MutableLiveData<Result?>()
    val token: LiveData<Result?> = _token

    private val _error = MutableLiveData<Exception?>()
    val error: LiveData<Exception?> = _error

    private val _signupState = MutableLiveData<String?>()
    val signupState: LiveData<String?> = _signupState


    fun login(signInData: SignInData) {
        viewModelScope.launch {
            val result = repository.login(signInData)
            if(result is ResultResponse.Success){
                _token.postValue(result.data)
                _error.postValue(null)
            }else if(result is ResultResponse.Error){
                _token.postValue(null)
                _error.postValue(result.exception)
            }
        }

    }

    fun signup(signUpData: SignUpData){
        viewModelScope.launch {
            val result = repository.signup(signUpData)
            if (result is ResultResponse.Success){
                _signupState.postValue(result.data)
                _error.postValue(null)
            }else if (result is ResultResponse.Error){
                _signupState.postValue(null)
                _error.postValue(result.exception)
            }

        }
    }

}