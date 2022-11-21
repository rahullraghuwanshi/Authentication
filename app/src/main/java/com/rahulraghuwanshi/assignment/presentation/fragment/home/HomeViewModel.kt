package com.rahulraghuwanshi.assignment.presentation.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth.CurrentUser
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val currentUser: CurrentUser
) : ViewModel() {

    suspend fun user(): FirebaseUser? =
        withContext(viewModelScope.coroutineContext) {
            currentUser.execute()
        }
}