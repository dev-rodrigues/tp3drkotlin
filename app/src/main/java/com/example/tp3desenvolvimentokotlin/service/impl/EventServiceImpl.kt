package com.example.tp3desenvolvimentokotlin.service.impl

import com.example.tp3desenvolvimentokotlin.domain.dao.impl.EventDAOImpl
import com.example.tp3desenvolvimentokotlin.domain.entity.Event
import com.example.tp3desenvolvimentokotlin.service.EventService
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

class EventServiceImpl:EventService {

    private var eventDAO: EventDAOImpl = EventDAOImpl()

    override fun register(event: Event): Task<Void> {
        return eventDAO.store(event);
    }

    override fun delete(event: Event): Task<Void> {
        return eventDAO.destroy(event.id!!)
    }

    override fun edit(event: Event):Task<Void>  {
        return eventDAO.update(event, event.id!!)
    }

    override fun findBy(email: String): Task<QuerySnapshot> {
        return eventDAO.findBy(email);
    }
}