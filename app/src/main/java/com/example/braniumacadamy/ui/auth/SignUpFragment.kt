package com.example.braniumacadamy.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.braniumacadamy.MyApplication
import com.example.braniumacadamy.data.model.auth.SignUpData
import com.example.braniumacadamy.databinding.FragmentSignUpBinding
import com.example.braniumacadamy.ui.viewmodel.UserViewModel
import com.example.braniumacadamy.ui.viewmodel.UserViewModelFactory
import com.google.android.material.snackbar.Snackbar

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        setupViewModel()
        setupListener()
        return binding.root
    }

    private fun setupListener() {
        binding.btnRegister.setOnClickListener{
            validateInputSignUp()
        }
        binding.tvLogin.setOnClickListener{
            negativeToLogin()
        }
    }

    private fun negativeToLogin() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    private fun validateInputSignUp() {
        val firstName = binding.editFirstName.text.toString().trim()
        val lastName = binding.editLastName.text.toString().trim()
        val email = binding.editEmail.text.toString().trim()
        val password = binding.editPassword.text.toString().trim()
        val confirmPassword = binding.editConfirmPassword.text.toString().trim()

        // Regular expression to check for at least one uppercase, one lowercase, one number, and one special character
        val passwordPattern =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{12,}\$".toRegex()

        when {
            firstName.isEmpty() -> {
                binding.editFirstName.error = "First name is required"
            }

            lastName.isEmpty() -> {
                binding.editLastName.error = "Last name is required"
            }

            email.isEmpty() -> {
                binding.editEmail.error = "Email is required"
            }

            password.isEmpty() -> {
                binding.editPassword.error = "Password is required"
            }

            password.length < 12 -> {
                binding.editPassword.error = "Password must be at least 12 characters long"
            }

            !password.matches(passwordPattern) -> {
                binding.editPassword.error =
                    "Password must include uppercase, lowercase, number, and special character"
            }

            password.contains(firstName, ignoreCase = true) || password.contains(
                lastName,
                ignoreCase = true
            ) || password.contains(email, ignoreCase = true) -> {
                binding.editPassword.error =
                    "Password should not be based on your personal information"
            }

            confirmPassword.isEmpty() -> {
                binding.editConfirmPassword.error = "Confirm password is required"
            }

            password != confirmPassword -> {
                binding.editConfirmPassword.error = "Passwords do not match"
            }

            else -> {
                // Password is valid, proceed to register
                val signUpData = SignUpData(firstName, lastName, email, password)
                viewModel.signup(signUpData)

            }
        }
    }


    private fun setupViewModel() {
        val repository = (requireActivity().application as MyApplication).repository
        viewModel = ViewModelProvider(requireActivity(),
            UserViewModelFactory(repository))[UserViewModel::class.java]
        viewModel.error.observe(viewLifecycleOwner){ex->
            ex?.let {
                Snackbar.make(binding.root, it.message.toString(), Snackbar.LENGTH_SHORT).show()
            }
        }
        viewModel.signupState.observe(viewLifecycleOwner){ex->
            ex?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }
}