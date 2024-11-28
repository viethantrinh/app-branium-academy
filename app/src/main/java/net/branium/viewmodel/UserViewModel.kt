package net.branium.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.branium.data.model.dto.request.user.UserInfoRequest
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.user.ImageResponse
import net.branium.data.model.dto.response.user.UserInfoResponse
import net.branium.data.repository.impl.UserRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepositoryImpl
): ViewModel(){
    private val _userInfo = MutableStateFlow(UserInfoResponse())
    val userInfo = _userInfo.asStateFlow()

    private val _userImage = MutableStateFlow(ImageResponse())
    val userImage = _userImage.asStateFlow()

    fun getUserInfo(){
        viewModelScope.launch {
            when (val response = userRepository.getStudentInfo()) {
                is ResultResponse.Success -> {
                    _userInfo.value = response.data ?: UserInfoResponse()
                }

                is ResultResponse.Error -> {
                    _userInfo.value = UserInfoResponse()
                }

                else -> {}
            }
        }
    }

    fun updateUserInfo(userInfoRequest: UserInfoRequest){
        viewModelScope.launch {
            when(val response = userRepository.updateStudentInfo(userInfoRequest)){
                is ResultResponse.Success -> {
//                    _userInfo.value = response.data ?: UserInfoResponse()
                    getUserInfo()
                }

                is ResultResponse.Error -> {
                    _userInfo.value = UserInfoResponse()
                }
                else -> {}
            }
        }
    }

    fun getUserImage(){
        viewModelScope.launch {
            when(val response = userRepository.getStudentImage()){
                is ResultResponse.Success -> {
                    _userImage.value = response.data ?: ImageResponse()
                }

                is ResultResponse.Error -> {
                    _userImage.value = ImageResponse()
                }
                else -> {}
            }
        }
    }

}