package com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth

import com.rahulraghuwanshi.assignment.domain.repository.AuthRepository

class SignoutUser(private val authRepository: AuthRepository) {
    suspend fun execute()= authRepository.signoutUser()
}