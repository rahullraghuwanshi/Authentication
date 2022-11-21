package com.rahulraghuwanshi.assignment.presentation.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.rahulraghuwanshi.assignment.R
import com.rahulraghuwanshi.assignment.common.toast
import com.rahulraghuwanshi.assignment.data.repository.firebase.auth.AuthRepositoryImpl
import com.rahulraghuwanshi.assignment.data.repository.firebase.auth.datasourceImpl.FirebaseAuthSourceImpl
import com.rahulraghuwanshi.assignment.databinding.FragmentHomeBinding
import com.rahulraghuwanshi.assignment.domain.usecase.firebase.auth.CurrentUser
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val user = FirebaseAuth.getInstance().currentUser
            if (user == null) {
                navigateToLoginScreen()
                return@launch
            }
            if (!user.isEmailVerified) {
                navigateToNotVerifiedScreen()
                return@launch
            }

            context?.toast("login")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val firebaseAuth = FirebaseAuth.getInstance()
//        val firebaseAuthSource = FirebaseAuthSourceImpl(firebaseAuth)
//        val authRepository = AuthRepositoryImpl(firebaseAuthSource)
//        val currentUser = CurrentUser(authRepository)
//        val viewModelProviderFactory = HomeViewModelFactory(currentUser)
//        val viewModel = ViewModelProvider(
//            this,
//            viewModelProviderFactory
//        )[HomeViewModel::class.java]
    }

    private fun navigateToLoginScreen() {
        findNavController().navigate(R.id.action_homeFragment_to_signinFragment)
    }

    private fun navigateToNotVerifiedScreen() {
        findNavController().navigate(R.id.action_homeFragment_to_notVerifiedFragment)
    }
}