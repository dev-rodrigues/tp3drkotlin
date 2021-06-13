package com.example.tp3desenvolvimentokotlin.ui.home.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp3desenvolvimentokotlin.service.EventService
import com.example.tp3desenvolvimentokotlin.service.FirebaseAuthService
import com.example.tp3desenvolvimentokotlin.ui.home.dashboard.dto.EventDTO

class ShowVaccineViewModel(
    private val firebaseAuthService: FirebaseAuthService,
    private val eventService: EventService
) : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun editEvent() {
        val edited = EventDTO.eventDTO!!

        var task = eventService.edit(edited)

        task
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Vacina editada com sucesso!"
            }
            .addOnFailureListener {
                _status.value = false
                _msg.value = "Falha ao editar vacina"
            }
    }

    fun delete() {
        val edited = EventDTO.eventDTO!!
        val task  = eventService.delete(edited)

        task
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Vacina excluida com sucesso!"
            }
            .addOnFailureListener {
                _status.value = false
                _msg.value = "Falha ao excluir vacina"
            }

    }

}