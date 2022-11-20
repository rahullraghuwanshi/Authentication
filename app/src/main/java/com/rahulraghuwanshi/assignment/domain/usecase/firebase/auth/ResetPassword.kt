package com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth

import com.rahulraghuwanshi.assignment.common.Resource
import com.rahulraghuwanshi.assignment.domain.repository.AuthRepository

class ResetPassword(private val authRepository: AuthRepository) {
    suspend fun execute(email: String): Resource<Boolean> = authRepository.resetPassword(email)
}