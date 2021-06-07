package com.example.tp3desenvolvimentokotlin.ui.auth.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp3desenvolvimentokotlin.service.FirebaseAuthService

class LoginViewModel(
    private val firebaseAuthService: FirebaseAuthService
) : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun authenticate(email: String, password: String) {
        val task = firebaseAuthService.signIn(email, password);

        task
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Autenticado com sucesso"
            }
            .addOnFailureListener {
                Log.e("LoginViewModel", "${it.message}")
                _msg.value = "${it.message}"
            }
    }

}