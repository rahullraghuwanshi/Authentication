package com.rahulraghuwanshi.assignment.presentation.fragment.signup

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
import com.rahulraghuwanshi.assignment.databinding.FragmentSignUpBinding
import com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth.SignInUser
import com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth.SignUpUser
import com.rahulraghuwanshi.assignment.presentation.fragment.signin.SignInViewModel
import com.rahulraghuwanshi.assignment.presentation.fragment.signin.SignInViewModelFactory
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog = context?.progressDialog()

        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseAuthSource = FirebaseAuthSourceImpl(firebaseAuth)
        val authRepository = AuthRepositoryImpl(firebaseAuthSource)
        val signUpUser = SignUpUser(authRepository)
        val viewModelProviderFactory = SignUpViewModelFactory(signUpUser)
        val viewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        )[SignUpViewModel::class.java]

        binding.signUpViewModel = viewModel
        binding.lifecycleOwner = this

        lifecycleScope.launch {
            viewModel.signUpResult.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Resource.Loading -> {
                        progressDialog?.show()
                    }
                    is Resource.Success -> {
                        progressDialog?.dismiss()
                        navigateToVerificaiton()
                    }
                    is Resource.Error -> {
                        progressDialog?.dismiss()
                        context?.toast(result.message.toString())
                    }
                }
            }
        }
    }

    private fun navigateToVerificaiton() {
        findNavController().navigate(R.id.action_signupFragment_to_verificationFragment)
    }
}