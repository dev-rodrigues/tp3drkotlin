package com.example.tp3desenvolvimentokotlin.ui.home.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp3desenvolvimentokotlin.domain.entity.Event
import com.example.tp3desenvolvimentokotlin.service.EventService
import com.example.tp3desenvolvimentokotlin.service.FirebaseAuthService

class IndexViewModel(
    private val firebaseAuthService: FirebaseAuthService,
    private val eventService: EventService
) : ViewModel() {

    private val _events = MutableLiveData<List<Event>>();
    val events: LiveData<List<Event>>
        get() = _events


    fun exitToApp() {
        firebaseAuthService.logout()
    }

    fun findVaccinesToUser() {
        val autenticatedUser = firebaseAuthService.getUser().email!!
        eventService.findBy(autenticatedUser).addOnSuccessListener {
            _events.value = it.toObjects(Event::class.java)
        }
    }
}