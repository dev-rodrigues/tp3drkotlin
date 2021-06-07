package com.example.tp3desenvolvimentokotlin.service.impl

import com.example.tp3desenvolvimentokotlin.domain.dao.impl.EventDAOImpl
import com.example.tp3desenvolvimentokotlin.domain.entity.Event
import com.example.tp3desenvolvimentokotlin.service.EventService
import com.google.android.gms.tasks.Task

class EventServiceImpl:EventService {

    private var eventDAO: EventDAOImpl = EventDAOImpl()

    override fun register(event: Event): Task<Void> {
        return eventDAO.store(event);
    }
}