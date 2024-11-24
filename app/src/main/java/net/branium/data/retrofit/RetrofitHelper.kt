package net.branium.data.retrofit

import net.branium.data.retrofit.interceptor.AuthInterceptor
import net.branium.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitHelper @Inject constructor(private val authInterceptor: AuthInterceptor) {
    /**
     * This retrofit instance is use to get retrofit instance without auth interceptor
     */
     fun getRetrofitInstance(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofitInstance = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofitInstance
    }

    /**
     * This retrofit instance is use to get retrofit instance with auth interceptor
     */
    fun getRetrofitInstanceWithAuthInterceptor(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS) // Thời gian chờ kết nối
            .readTimeout(30, TimeUnit.SECONDS)    // Thời gian chờ đọc dữ liệu
            .writeTimeout(30, TimeUnit.SECONDS)   // Thời gian chờ ghi dữ liệu
            .build()

        val retrofitInstance = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofitInstance
    }

}