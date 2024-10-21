package net.branium.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.branium.data.model.dto.response.home.Category
import net.branium.data.model.dto.response.home.PopularCourse
import net.branium.data.model.dto.response.home.TopPick
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.repository.impl.HomeRepositoryImp
import net.branium.di.CartQuantityManager
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepositoryImp,
    private val cartQuantityManager: CartQuantityManager
) : ViewModel() {
    private var _apiResponseState = mutableStateOf<ApiResponseState?>(null)
    val apiResponseState: State<ApiResponseState?> = _apiResponseState

    private var _popularCourses = mutableStateOf(emptyList<PopularCourse>())
    val popularCourses: State<List<PopularCourse>> = _popularCourses

    private var _categories = mutableStateOf(emptyList<Category>())
    val categories: State<List<Category>> = _categories

    private var _topPicks = mutableStateOf(emptyList<TopPick>())
    val topPicks: State<List<TopPick>> = _topPicks

    private val _cartQuantity = mutableIntStateOf(cartQuantityManager.getCartQuantity())
    val cartQuantity = _cartQuantity

    init {
        getHomeData()
    }

    private fun getHomeData() {
        viewModelScope.launch {
            when (val response = homeRepository.getHomePage()) {
                is ResultResponse.Success -> {
                    _popularCourses.value = response.data?.popularCourses ?: emptyList()
                    _categories.value = response.data?.categories ?: emptyList()
                    _topPicks.value = response.data?.topPicks ?: emptyList()
                    _cartQuantity.intValue = response.data?.cartQuantities ?: 0
                    cartQuantityManager.storeCartQuantity(_cartQuantity.intValue)
                    _apiResponseState.value = ApiResponseState.Succeeded
                    _apiResponseState.value?.message = "load success"
                }

                is ResultResponse.Error -> {
                    _popularCourses.value = emptyList()
                    _categories.value = emptyList()
                    _topPicks.value = emptyList()
                    _cartQuantity.intValue = 0
                    cartQuantityManager.storeCartQuantity(0)
                    _apiResponseState.value = ApiResponseState.Failed
                    _apiResponseState.value?.message = "load failed"
                }

                else -> {
                    _cartQuantity.intValue = 0
                    cartQuantityManager.storeCartQuantity(0)
                    _apiResponseState.value = ApiResponseState.Processing
                    _apiResponseState.value?.message = "Processing"
                }
            }
        }

    }

    fun updateCartQuantity(newQuantity: Int) {
        _cartQuantity.intValue = newQuantity
        cartQuantityManager.storeCartQuantity(newQuantity)
    }
}