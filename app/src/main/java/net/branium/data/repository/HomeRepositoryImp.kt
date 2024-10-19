package net.branium.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.branium.data.model.dto.response.HomeResponse
import net.branium.data.retrofit.HomeApiService
import net.branium.data.retrofit.ResultResponse
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class HomeRepositoryImp
@Inject constructor(@Named("RetrofitInstanceWithAuthInterceptor") val retrofitInstance: Retrofit) : HomeRepository{
    private val homeApiService: HomeApiService by lazy {
        retrofitInstance.create(HomeApiService::class.java)
    }

    override suspend fun getHomePage(): ResultResponse<HomeResponse> {
        return withContext(Dispatchers.IO) {
            val response = homeApiService.getHomePageData()
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse != null){
                    if(apiResponse.code == 1000){
                        ResultResponse.Success(apiResponse.result)
                    } else {
                        ResultResponse.Error(Exception(apiResponse.message))
                    }
                }else{
                    ResultResponse.Error(Exception("Response body is null"))
                }
            } else {
                ResultResponse.Error(Exception("Network error: ${response.code()} - ${response.message()}"))
            }
        }
    }
}
