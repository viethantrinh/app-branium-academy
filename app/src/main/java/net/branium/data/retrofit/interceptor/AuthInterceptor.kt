package net.branium.data.retrofit.interceptor

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import net.branium.di.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request().newBuilder()
        val headerName = "Authorization"
        val tokenManager = TokenManager(context)
        val token = tokenManager.getToken()
        val sb = StringBuilder("")
        val headerValue = sb.append("Bearer").append(" ").append(token)
        currentRequest.addHeader(headerName, headerValue.toString())
        val newRequest = currentRequest.build();
        return chain.proceed(newRequest)
    }
}