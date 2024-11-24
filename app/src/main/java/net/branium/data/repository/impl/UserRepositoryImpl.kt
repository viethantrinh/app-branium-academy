package net.branium.data.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.branium.data.model.dto.request.user.UserInfoRequest
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.user.ImageResponse
import net.branium.data.model.dto.response.user.UserInfoResponse
import net.branium.data.repository.UserRepository
import net.branium.data.retrofit.service.UserApiService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class UserRepositoryImpl
@Inject constructor(@Named("RetrofitInstanceWithAuthInterceptor") private val retrofitInstanceWithAuthInterceptor: Retrofit) :
    UserRepository {
    private val userApiService: UserApiService by lazy {
        retrofitInstanceWithAuthInterceptor.create(UserApiService::class.java)
    }

    override suspend fun getStudentInfo(): ResultResponse<UserInfoResponse> {
        return withContext(Dispatchers.IO) {
            val response = userApiService.getStudentInfo()
            if (response.isSuccessful) {
                val apiRepository = response.body()
                if (apiRepository != null) {
                    if (apiRepository.code == 1000) {
                        ResultResponse.Success(apiRepository.result)
                    } else {
                        ResultResponse.Error(Exception(apiRepository.message))
                    }
                } else {
                    ResultResponse.Error(Exception("Something went wrong"))
                }
            } else {
                ResultResponse.Error(Exception("No Internet Connection"))
            }
        }
    }

    override suspend fun updateStudentInfo(userInfoRequest: UserInfoRequest): ResultResponse<UserInfoResponse> {
        return withContext(Dispatchers.IO) {
            val response = userApiService.updateStudentInfo(userInfoRequest)
            if (response.isSuccessful) {
                val apiRepository = response.body()
                if (apiRepository != null) {
                    if (apiRepository.code == 1000) {
                        ResultResponse.Success(apiRepository.result)
                    } else {
                        ResultResponse.Error(Exception(apiRepository.message))
                    }
                } else {
                    ResultResponse.Error(Exception("Something went wrong"))
                }
            } else {
                ResultResponse.Error(Exception("No Internet Connection"))
            }
        }
    }

    override suspend fun getStudentImage(): ResultResponse<ImageResponse> {
        return withContext(Dispatchers.IO) {
            val response = userApiService.getStudentImage()
            if (response.isSuccessful) {
                val apiRepository = response.body()
                if (apiRepository != null) {
                    if (apiRepository.code == 1000) {
                        ResultResponse.Success(apiRepository.result)
                    } else {
                        ResultResponse.Error(Exception(apiRepository.message))
                    }
                } else {
                    ResultResponse.Error(Exception("Something went wrong"))
                }
            } else {
                ResultResponse.Error(Exception("No Internet Connection"))
            }
        }
    }

}

