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
import net.branium.data.model.dto.response.search.SearchResponse
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
    val isFocused : MutableStateFlow<Boolean> = _isFocused

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet = _showBottomSheet.asStateFlow()


    private val _searchResponse = MutableStateFlow(SearchResponse())
    val searchResponse : MutableStateFlow<SearchResponse> = _searchResponse

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
                }else {
                    _searchResponse.value = SearchResponse()
                }
            }
            .launchIn(viewModelScope)
    }
    fun getCourses(query: String) {
        viewModelScope.launch {
            _isSearching.value = true
            when(val response = searchRepository.getInfoByKeyword(keyword = query)) {
                is ResultResponse.Success -> {
                   _searchResponse.value = response.data ?: SearchResponse()
                }

                is ResultResponse.Error -> {
                    _searchResponse.value = SearchResponse()
                }

                else -> {}
            }
            _isSearching.value = false
        }
    }

    fun pagingCourses(query: String, page: Int, size: Int) {
        viewModelScope.launch {
            _isSearching.value = true
            when(val response = searchRepository.getInfoByKeyword(keyword = query, page = page, size = size)) {
                is ResultResponse.Success -> {
                    _searchResponse.value = response.data ?: SearchResponse()
                }

                is ResultResponse.Error -> {
                    _searchResponse.value = SearchResponse()
                }

                else -> {}
            }
            _isSearching.value = false
        }
    }


}