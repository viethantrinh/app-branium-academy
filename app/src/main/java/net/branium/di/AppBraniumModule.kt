package net.branium.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.branium.data.retrofit.RetrofitHelper
import net.branium.data.retrofit.interceptor.AuthInterceptor
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppBraniumModule {

    @Provides
    @Singleton
    fun provideTokenManager(
        @ApplicationContext context: Context
    ): TokenManager {
        return TokenManager(context)
    }

    @Provides
    @Singleton
    fun provideCartQuantityManager(
        @ApplicationContext context: Context
    ): CartQuantityManager {
        return CartQuantityManager(context)
    }

    @Provides
    @Singleton
    fun provideRetrofitHelper(
        authInterceptor: AuthInterceptor
    ): RetrofitHelper {
        return RetrofitHelper(authInterceptor)
    }

    @Provides
    @Singleton
    @Named("RetrofitInstance")
    fun provideRetrofitInstance(retrofitHelper: RetrofitHelper): Retrofit {
        return retrofitHelper.getRetrofitInstance()
    }

    @Provides
    @Singleton
    @Named("RetrofitInstanceWithAuthInterceptor")
    fun provideRetrofitInstanceWithAuthInterceptor(retrofitHelper: RetrofitHelper): Retrofit {
        return retrofitHelper.getRetrofitInstanceWithAuthInterceptor() // Hoặc instance với Auth nếu cần
    }
}