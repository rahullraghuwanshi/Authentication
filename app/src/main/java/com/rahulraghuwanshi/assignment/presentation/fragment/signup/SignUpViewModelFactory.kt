package com.rahulraghuwanshi.assignment.presentation.fragment.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth.SignInUser
import com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth.SignUpUser
import com.rahulraghuwanshi.assignment.presentation.fragment.signin.SignInViewModel

class SignUpViewModelFactory(private val signUpUser: SignUpUser) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(signUpUser) as T
        }
        throw IllegalArgumentException("Something is wrong with viewmodel!!")
    }
}