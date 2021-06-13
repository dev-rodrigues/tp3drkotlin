package com.example.tp3desenvolvimentokotlin.service

import com.example.tp3desenvolvimentokotlin.domain.entity.Event
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface EventService {
    fun register(event: Event): Task<Void>;
    fun delete(event: Event):Task<Void>;
    fun edit(event: Event): Task<Void>;
    fun findBy(email:String):Task<QuerySnapshot>;
}