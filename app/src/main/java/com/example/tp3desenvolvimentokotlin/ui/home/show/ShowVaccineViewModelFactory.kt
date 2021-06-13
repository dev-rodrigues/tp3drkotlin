package com.example.tp3desenvolvimentokotlin.ui.home.show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tp3desenvolvimentokotlin.service.EventService
import com.example.tp3desenvolvimentokotlin.service.FirebaseAuthService
import com.example.tp3desenvolvimentokotlin.ui.home.registerVaccine.RegisterVaccineViewModel

class ShowVaccineViewModelFactory(
    private val firebaseAuthService: FirebaseAuthService,
    private val eventService: EventService
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShowVaccineViewModel::class.java))
            return ShowVaccineViewModel(firebaseAuthService, eventService) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}