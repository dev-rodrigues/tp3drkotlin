package com.example.tp3desenvolvimentokotlin.service

import com.example.tp3desenvolvimentokotlin.domain.entity.Event
import com.google.android.gms.tasks.Task

interface EventService {
    fun register(event: Event): Task<Void>;
}