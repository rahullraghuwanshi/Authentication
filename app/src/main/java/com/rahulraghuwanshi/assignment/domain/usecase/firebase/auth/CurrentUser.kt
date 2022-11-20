package com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth

import com.google.firebase.auth.FirebaseUser
import com.rahulraghuwanshi.assignment.domain.repository.AuthRepository

class CurrentUser(private val authRepository: AuthRepository) {
    suspend fun execute(): FirebaseUser? = authRepository.currentUser()
}