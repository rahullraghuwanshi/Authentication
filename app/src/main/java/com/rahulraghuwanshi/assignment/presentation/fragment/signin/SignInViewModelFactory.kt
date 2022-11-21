package com.rahulraghuwanshi.assignment.presentation.fragment.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth.CurrentUser
import com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth.SignInUser
import com.rahulraghuwanshi.assignment.presentation.fragment.home.HomeViewModel

class SignInViewModelFactory(private val signInUser: SignInUser) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(signInUser) as T
        }
        throw IllegalArgumentException("Something is wrong with viewmodel!!")
    }
}