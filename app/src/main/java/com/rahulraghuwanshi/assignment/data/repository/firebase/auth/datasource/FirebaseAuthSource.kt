package com.rahulraghuwanshi.assignment.data.repository.firebase.auth.datasource

import com.google.firebase.auth.FirebaseUser
import com.rahulraghuwanshi.assignment.common.Resource

interface FirebaseAuthSource {

    suspend fun signinUser(email: String, password: String): Resource<FirebaseUser>
    suspend fun signupUser(email: String, password: String): Resource<FirebaseUser>
    suspend fun resetPassword(email: String): Resource<Boolean>
    suspend fun signoutUser()
    suspend fun currentUser(): FirebaseUser?
}