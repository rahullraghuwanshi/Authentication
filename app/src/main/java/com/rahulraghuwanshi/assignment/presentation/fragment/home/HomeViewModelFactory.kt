package com.rahulraghuwanshi.assignment.presentation.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth.CurrentUser

class HomeViewModelFactory(val currentUser: CurrentUser) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(currentUser) as T
        }
        throw IllegalArgumentException("Something is wrong with viewmodel!!")
    }
}