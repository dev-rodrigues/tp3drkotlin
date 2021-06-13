package com.example.tp3desenvolvimentokotlin.ui.home.registerVaccine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp3desenvolvimentokotlin.domain.entity.Event
import com.example.tp3desenvolvimentokotlin.domain.entity.Release
import com.example.tp3desenvolvimentokotlin.service.EventService
import com.example.tp3desenvolvimentokotlin.service.FirebaseAuthService
import java.util.*

class RegisterVaccineViewModel(
    private val firebaseAuthService: FirebaseAuthService,
    private val eventService: EventService

    ) : ViewModel() {


    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg


    fun register(vaccineName: String?, totalDoses: Int?, nextVaccine: String) {
        val user = firebaseAuthService.getUser().email
        val event = Event(null, user, nextVaccine, vaccineName, totalDoses)
        event.releases = listOf(Release(null, Calendar.getInstance().time))

        val task = eventService.register(event)

        task
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Vacina registrada com sucesso!"
            }
            .addOnFailureListener {
                _status.value = false
                _msg.value = "Falha ao registrar lancamento"
            }

    }
}