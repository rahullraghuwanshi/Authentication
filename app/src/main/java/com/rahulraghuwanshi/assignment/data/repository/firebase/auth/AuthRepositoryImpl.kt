package com.rahulraghuwanshi.assignment.data.repository.firebase.auth

import com.google.firebase.auth.FirebaseUser
import com.rahulraghuwanshi.assignment.common.Resource
import com.rahulraghuwanshi.assignment.data.repository.firebase.auth.datasource.FirebaseAuthSource
import com.rahulraghuwanshi.assignment.domain.repository.AuthRepository

class AuthRepositoryImpl(private val firebaseAuthSource: FirebaseAuthSource) : AuthRepository {

    override suspend fun signinUser(email: String, password: String): Resource<FirebaseUser> {
        return firebaseAuthSource.signinUser(email, password)
    }

    override suspend fun signupUser(email: String, password: String): Resource<FirebaseUser> {
        return firebaseAuthSource.signupUser(email, password)
    }

    override suspend fun resetPassword(email: String): Resource<Boolean> {
        return firebaseAuthSource.resetPassword(email)
    }

    override suspend fun signoutUser() {
        firebaseAuthSource.signoutUser()
    }

    override suspend fun currentUser(): FirebaseUser? {
        return firebaseAuthSource.currentUser()
    }
}