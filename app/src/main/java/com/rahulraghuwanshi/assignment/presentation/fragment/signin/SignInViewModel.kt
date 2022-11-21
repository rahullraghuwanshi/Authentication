package com.rahulraghuwanshi.assignment.presentation.fragment.signin

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseUser
import com.rahulraghuwanshi.assignment.R
import com.rahulraghuwanshi.assignment.common.Resource
import com.rahulraghuwanshi.assignment.common.isValidEmail
import com.rahulraghuwanshi.assignment.common.toast
import com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth.SignInUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(
    private val signInUser: SignInUser
) : ViewModel() {

    private var _signInResult = MutableLiveData<Resource<FirebaseUser>>()
    val signInResult: LiveData<Resource<FirebaseUser>>
        get() = _signInResult

    //email and password for the input
    var email: String? = null
    var password: String? = null

    //function to perform login
    fun login(view: View) {

        if (email.isNullOrEmpty() || password.isNullOrEmpty() ) {
            _signInResult.value = Resource.Error("All fields are necessary")
            return
        }

        if (!email.isValidEmail()){
            _signInResult.value = Resource.Error("Please Enter Valid E-Mail")
            return
        }

        if (password?.length!! < 8) {
            _signInResult.value = Resource.Error("Please Enter Valid Password greater than 8")
            return
        }

        _signInResult.value = Resource.Loading(null)

        //calling login from repository to perform the actual authentication
        viewModelScope.launch {
            val signIn = withContext(Dispatchers.IO) {
                signInUser.execute(email!!, password!!)
            }

            _signInResult.value = signIn
        }
    }

    fun navigateToSignUp(view: View) =
        Navigation.findNavController(view).navigate(R.id.action_signinFragment_to_signupFragment)

    fun navigateToForgot(view: View) {
        view.context?.toast("Currently not working due to lack of time")
    }
}