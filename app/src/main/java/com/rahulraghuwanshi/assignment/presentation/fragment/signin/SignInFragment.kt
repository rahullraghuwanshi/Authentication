package com.rahulraghuwanshi.assignment.presentation.fragment.signin

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.rahulraghuwanshi.assignment.R
import com.rahulraghuwanshi.assignment.common.Resource
import com.rahulraghuwanshi.assignment.common.progressDialog
import com.rahulraghuwanshi.assignment.common.toast
import com.rahulraghuwanshi.assignment.data.repository.firebase.auth.AuthRepositoryImpl
import com.rahulraghuwanshi.assignment.data.repository.firebase.auth.datasourceImpl.FirebaseAuthSourceImpl
import com.rahulraghuwanshi.assignment.databinding.FragmentSignInBinding
import com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth.SignInUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private var progressDialog: Dialog? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog = context?.progressDialog()

        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseAuthSource = FirebaseAuthSourceImpl(firebaseAuth)
        val authRepository = AuthRepositoryImpl(firebaseAuthSource)
        val signInUser = SignInUser(authRepository)
        val viewModelProviderFactory = SignInViewModelFactory(signInUser)
        val viewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        )[SignInViewModel::class.java]

        binding.signInViewModel = viewModel
        binding.lifecycleOwner = this

        lifecycleScope.launch {
            viewModel.signInResult.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Resource.Loading -> {
                        progressDialog?.show()
                    }
                    is Resource.Success -> {
                        progressDialog?.dismiss()
                        navigateToHome()
                    }
                    is Resource.Error -> {
                        progressDialog?.dismiss()
                        context?.toast(result.message.toString())
                    }
                }
            }
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.action_signinFragment_to_homeFragment)
    }
}