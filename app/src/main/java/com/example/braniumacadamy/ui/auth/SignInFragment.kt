package com.example.braniumacadamy.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.braniumacadamy.MyApplication
import com.example.braniumacadamy.R
import com.example.braniumacadamy.data.model.auth.SignInData
import com.example.braniumacadamy.databinding.FragmentSignInBinding
import com.example.braniumacadamy.ui.viewmodel.UserViewModel
import com.example.braniumacadamy.ui.viewmodel.UserViewModelFactory
import com.google.android.material.snackbar.Snackbar

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        setupListener()
        setupViewModel()
        return binding.root
    }

    private fun setupViewModel() {
        val repository = (requireActivity().application as MyApplication).repository  //lay ra repository tu application
        viewModel = ViewModelProvider(requireActivity(),
            UserViewModelFactory(repository))[UserViewModel::class.java] // khoi tao viewmodel
        viewModel.error.observe(viewLifecycleOwner){ex ->
            ex?.let {
                showProgressBar()
                Snackbar.make(binding.root, it.message.toString(), Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupListener() {
        binding.btnLogin.setOnClickListener{
            val email = binding.editEmail.text.toString();
            val password = binding.editPassword.text.toString()
            val loginData = SignInData(email, password)
            viewModel.login(loginData)
            hideKeyboard()
            showProgressBar(View.VISIBLE) // khi ấn login sẽ cho hiên progressbar lên

        }
        binding.tvRegisterNow.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view_auth, SignUpFragment::class.java, null)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun showProgressBar(visibility: Int = View.GONE) {
        binding.progressBar.visibility = visibility
    }

    private fun hideKeyboard() {
        val imm = requireActivity()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(binding.btnLogin.windowToken, 0)    }
}