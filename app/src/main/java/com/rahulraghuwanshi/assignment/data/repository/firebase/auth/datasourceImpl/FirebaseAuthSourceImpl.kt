package com.rahulraghuwanshi.assignment.data.repository.firebase.auth.datasourceImpl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rahulraghuwanshi.assignment.common.Resource
import com.rahulraghuwanshi.assignment.data.repository.firebase.auth.datasource.FirebaseAuthSource
import kotlinx.coroutines.tasks.await

class FirebaseAuthSourceImpl(private val firebaseAuth: FirebaseAuth) : FirebaseAuthSource {

    override suspend fun signinUser(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun signupUser(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user!!.sendEmailVerification().await()
            return Resource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun resetPassword(email: String): Resource<Boolean> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            return Resource.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.toString(), false)
        }
    }

    override suspend fun signoutUser() {
        firebaseAuth.signOut()
    }

    override suspend fun currentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}