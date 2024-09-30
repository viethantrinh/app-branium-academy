package com.example.braniumacadamy

import android.app.Application
import com.example.braniumacadamy.data.repository.auth.UserRepository
import com.example.braniumacadamy.data.repository.auth.UserRepositoryImpl

class MyApplication: Application() {
    lateinit var  repository: UserRepository

    override fun onCreate() {
        super.onCreate()
        // Khởi tạo repository một lần khi ứng dụng bắt đầu
        repository = UserRepositoryImpl()
    }
}