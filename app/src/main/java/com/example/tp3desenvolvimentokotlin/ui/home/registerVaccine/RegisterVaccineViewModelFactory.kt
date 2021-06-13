package com.example.tp3desenvolvimentokotlin.ui.home.registerVaccine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tp3desenvolvimentokotlin.service.EventService
import com.example.tp3desenvolvimentokotlin.service.FirebaseAuthService

class RegisterVaccineViewModelFactory(
    private val firebaseAuthService: FirebaseAuthService,
    private val eventService: EventService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterVaccineViewModel::class.java))
            return RegisterVaccineViewModel(firebaseAuthService, eventService) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}