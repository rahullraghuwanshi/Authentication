package com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth

import com.rahulraghuwanshi.assignment.domain.repository.AuthRepository

class SignOutUser(private val authRepository: AuthRepository) {
    suspend fun execute()= authRepository.signoutUser()
}