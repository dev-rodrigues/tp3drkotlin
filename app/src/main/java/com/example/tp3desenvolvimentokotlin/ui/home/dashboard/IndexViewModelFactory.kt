package com.example.tp3desenvolvimentokotlin.ui.home.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tp3desenvolvimentokotlin.service.EventService
import com.example.tp3desenvolvimentokotlin.service.FirebaseAuthService

class IndexViewModelFactory(
    private val firebaseAuthService: FirebaseAuthService,
    private val eventService: EventService
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IndexViewModel::class.java))
            return IndexViewModel(firebaseAuthService, eventService) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}