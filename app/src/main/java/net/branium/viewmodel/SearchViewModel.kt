package net.branium.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.course.CourseResponse
import net.branium.data.repository.impl.CategoryRepositoryImpI
import net.branium.data.repository.impl.SearchRepositoryImpl
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel
@Inject constructor(private val searchRepository: SearchRepositoryImpl): ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isFocused = MutableStateFlow(false)
    val isFocused = _isFocused.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet = _showBottomSheet.asStateFlow()

    private val _coursesSearch = MutableStateFlow(listOf<CourseResponse>())
    val coursesSearch = _coursesSearch.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
    fun updateFocusState(focused: Boolean) {
        _isFocused.value = focused
    }
    fun showBottomSheet() {
        _showBottomSheet.value = true
    }
    fun hideBottomSheet() {
        _showBottomSheet.value = false
    }

    init {
        // Observe search text and trigger search after debounce
        _searchText
            .debounce(1000L) // Đợi 1 giây sau khi nhập xong
            .onEach { query ->
                if (query.isNotEmpty()) {
                    getCourses(query)
                }
            }
            .launchIn(viewModelScope)
    }
    fun getCourses(query: String) {
        viewModelScope.launch {
            _isSearching.value = false
            when(val response = searchRepository.getInfoByKeyword(query)) {
                is ResultResponse.Success -> {
                    _coursesSearch.value = response.data?.content ?: emptyList()
                }

                is ResultResponse.Error -> {
                    _coursesSearch.value = emptyList()
                }

                else -> {}
            }
            _isSearching.value = true
        }
    }



}