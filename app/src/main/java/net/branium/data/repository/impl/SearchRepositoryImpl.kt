package net.branium.data.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.search.SearchResponse
import net.branium.data.repository.SearchRepository
import net.branium.data.retrofit.service.SearchApiService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class SearchRepositoryImpl
@Inject constructor(@Named("RetrofitInstanceWithAuthInterceptor") val retrofitInstance: Retrofit) :
    SearchRepository {
        private val searchApiService: SearchApiService by lazy {
            retrofitInstance.create(SearchApiService::class.java)
        }

    override suspend fun getInfoByKeyword(query: String): ResultResponse<SearchResponse> {
        return withContext(Dispatchers.IO){
            val response = searchApiService.getInfoByKeyword(query)
            if (response.isSuccessful){
                val apiResponse = response.body()
                if (apiResponse != null){
                    if (apiResponse.code == 1000){
                        ResultResponse.Success(apiResponse.result)
                    }else{
                        ResultResponse.Error(Exception(apiResponse.message))
                    }
                }else{
                    ResultResponse.Error(Exception("Response body is null"))
                }
            }else{
                ResultResponse.Error(Exception("Something went wrong"))
            }
        }
    }

}