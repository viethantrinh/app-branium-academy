package net.branium.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.branium.data.model.dto.home.Category
import net.branium.data.model.dto.home.PopularCourse
import net.branium.data.model.dto.home.TopPick
import net.branium.data.repository.HomeRepositoryImp
import net.branium.data.retrofit.ResultResponse
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepositoryImp) : ViewModel() {
    private var _apiResponseState = mutableStateOf<ApiResponseState?>(null)
    val apiResponseState: State<ApiResponseState?> = _apiResponseState

    private var  _cartQuantities = mutableStateOf(0)
    val cartQuantities: State<Int> = _cartQuantities

    private var _popularCourses = mutableStateOf(emptyList<PopularCourse>())
    val popularCourses: State<List<PopularCourse>> = _popularCourses

    private var  _categories = mutableStateOf(emptyList<Category>())
    val categories: State<List<Category>> = _categories

    private var _topPicks = mutableStateOf(emptyList<TopPick>())
    val topPicks: State<List<TopPick>> = _topPicks

    init {
        getHomeData()
    }

    fun getHomeData() {
       viewModelScope.launch {
           when(val response = homeRepository.getHomePage()){
               is ResultResponse.Success -> {
                   _apiResponseState.value = ApiResponseState.Succeeded
                   _apiResponseState.value?.message = "load success"
                   _popularCourses.value = response.data?.popularCourses ?: emptyList()
                   _categories.value = response.data?.categories ?: emptyList()
                   _topPicks.value = response.data?.topPicks ?: emptyList()
               }

               is ResultResponse.Error ->{
                   _apiResponseState.value = ApiResponseState.Failed
                   _apiResponseState.value?.message = "load failed"
                   _popularCourses.value = emptyList()
                   _categories.value = emptyList()
                   _topPicks.value = emptyList()
               }
               else ->{
                   _apiResponseState.value = ApiResponseState.Processing
                   _apiResponseState.value?.message = "Processing"
               }
           }
       }

    }


}