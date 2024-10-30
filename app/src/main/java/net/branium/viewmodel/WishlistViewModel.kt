package net.branium.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.course.CourseResponse
import net.branium.data.repository.impl.WishlistRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(val wishlistRepository: WishlistRepositoryImpl) :
    ViewModel() {
    private var _apiResponseState = mutableStateOf<ResponseState?>(null)
    val apiResponseState: State<ResponseState?> = _apiResponseState

    private var _wishListItems = mutableStateOf<List<CourseResponse>>(emptyList())
    val wishListItems: State<List<CourseResponse>> = _wishListItems

    init {
        getAllWishListItems()
    }

    fun getAllWishListItems() {
        viewModelScope.launch {
            _apiResponseState.value = ResponseState.Processing("Processing")
            when (val response = wishlistRepository.listWishlistItems()) {
                is ResultResponse.Success -> {
                    _wishListItems.value = response.data ?: emptyList()
                    _apiResponseState.value = ResponseState.Succeeded("Succeeded")
                }
                is ResultResponse.Error -> {
                    _wishListItems.value = emptyList()
                    _apiResponseState.value = ResponseState.Failed("Failed")
                }
            }
        }
    }

    fun addWishListItem(courseId: Int) {
        viewModelScope.launch {
            _apiResponseState.value = ResponseState.Processing("Processing")
            when (val response = wishlistRepository.addWishlistItem(courseId)) {
                is ResultResponse.Success -> {
                    response.data?.let {
                        _wishListItems.value = it
                    }
                    _apiResponseState.value = ResponseState.Succeeded("Succeeded")
                }
                is ResultResponse.Error -> {
                    _apiResponseState.value = ResponseState.Failed("Failed")
                }
            }
        }
    }

    fun removeWishListItem(courseId: Int) {
        viewModelScope.launch {
            _apiResponseState.value = ResponseState.Processing("Processing")
            when (val response = wishlistRepository.removeWishlistItem(courseId)) {
                is ResultResponse.Success -> {
                    response.data?.let {
                        _wishListItems.value = it
                    }
                    _apiResponseState.value = ResponseState.Succeeded("Succeeded")
                }
                is ResultResponse.Error -> {
                    _apiResponseState.value = ResponseState.Failed("Failed")
                }
            }
        }
    }

}

