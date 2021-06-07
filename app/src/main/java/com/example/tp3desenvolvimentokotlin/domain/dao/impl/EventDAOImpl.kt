package com.example.tp3desenvolvimentokotlin.domain.dao.impl

import com.example.tp3desenvolvimentokotlin.domain.dao.EventDAO
import com.example.tp3desenvolvimentokotlin.domain.entity.Event
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class EventDAOImpl: EventDAO {

    var db = FirebaseFirestore.getInstance().collection("event")

    override fun store(obj: Event): Task<Void> {
        throw NotImplementedError()
    }

    override fun destroy(type: String): Task<Void> {
        throw NotImplementedError()
    }

    override fun update(obj: Event, key: String): Task<Void> {
        throw NotImplementedError()
    }

    override fun findBy(key: String): Task<QuerySnapshot> {
        throw NotImplementedError()
    }

    override fun findAll(): Task<QuerySnapshot> {
        throw NotImplementedError()
    }
}