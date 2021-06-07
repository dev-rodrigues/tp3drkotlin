package com.example.tp3desenvolvimentokotlin.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tp3desenvolvimentokotlin.service.FirebaseAuthService
import com.example.tp3desenvolvimentokotlin.service.UserDetailService

class RegisterViewModelFactory(
    private val firebaseAuthService: FirebaseAuthService,
    private val userDetailService: UserDetailService
): ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java))
            return RegisterViewModel(firebaseAuthService, userDetailService) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}