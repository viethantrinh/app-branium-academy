package net.branium.data.retrofit

import net.branium.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val retrofitInstance = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun getInstance(): Retrofit {
        return retrofitInstance
    }
}