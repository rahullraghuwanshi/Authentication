package com.rahulraghuwanshi.assignment.presentation.fragment.signup

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
import com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth.SignUpUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel(
    private val signUpUser: SignUpUser
) : ViewModel() {

    private var _signUpResult = MutableLiveData<Resource<FirebaseUser>>()
    val signUpResult: LiveData<Resource<FirebaseUser>>
        get() = _signUpResult

    //email and password for the input
    var fullName: String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null

    //function to perform login
    fun signUp(view: View) {
        if (fullName.isNullOrEmpty()
            || email.isNullOrEmpty()
            || password.isNullOrEmpty()
            || confirmPassword.isNullOrEmpty()
        ) {
            _signUpResult.value = Resource.Error("All fields are necessary")
            return
        }
        if (!email.isValidEmail()) {
            _signUpResult.value = Resource.Error("Please Enter Valid E-Mail")
            return
        }
        if (!password.equals(confirmPassword)) {
            _signUpResult.value = Resource.Error("Password not match")
            return
        }
        if (password?.length!! < 8 || confirmPassword?.length!! < 8) {
            _signUpResult.value = Resource.Error("Password length should not less than 8")
            return
        }

        _signUpResult.value = Resource.Loading(null)

        //calling login from repository to perform the actual authentication
        viewModelScope.launch {
            val signUp = withContext(Dispatchers.IO) {
                signUpUser.execute(email!!, password!!)
            }

            _signUpResult.value = signUp
        }
    }

    fun navigateToSignIn(view: View) =
        Navigation.findNavController(view)
            .navigate(R.id.action_signupFragment_to_signinFragment)

}