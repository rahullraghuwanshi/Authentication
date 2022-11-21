package com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth

import com.google.firebase.auth.FirebaseUser
import com.rahulraghuwanshi.assignment.common.Resource
import com.rahulraghuwanshi.assignment.domain.repository.AuthRepository

class SignInUser(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String): Resource<FirebaseUser> =
        authRepository.signinUser(email, password)
}