package com.rahulraghuwanshi.assignment.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.rahulraghuwanshi.assignment.common.Resource

interface AuthRepository {
    suspend fun signinUser(email: String, password: String): Resource<FirebaseUser>
    suspend fun signupUser(email: String, password: String): Resource<FirebaseUser>
    suspend fun resetPassword(email: String): Resource<Boolean>
    suspend fun signoutUser()
    suspend fun currentUser(): FirebaseUser?
}