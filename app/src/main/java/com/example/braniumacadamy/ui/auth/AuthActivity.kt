package com.example.braniumacadamy.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.braniumacadamy.MyApplication
import com.example.braniumacadamy.R
import com.example.braniumacadamy.databinding.ActivityMainBinding
import com.example.braniumacadamy.ui.MainActivity
import com.example.braniumacadamy.ui.viewmodel.UserViewModel
import com.example.braniumacadamy.ui.viewmodel.UserViewModelFactory

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViewModel()
        setupAction()
    }

    private fun setupAction() {

    }

    private fun setupViewModel() {
        val repository = (application as MyApplication).repository
        viewModel = ViewModelProvider(
            this,
            UserViewModelFactory(repository)
        )[UserViewModel::class.java]

        viewModel.token.observe(this) { token ->
            token?.let {
                navigateToHome()
            }
        }

        viewModel.signupState.observe(this){

        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}