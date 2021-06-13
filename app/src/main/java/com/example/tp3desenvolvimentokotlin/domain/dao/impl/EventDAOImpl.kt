package com.example.tp3desenvolvimentokotlin.domain.dao.impl

import com.example.tp3desenvolvimentokotlin.domain.dao.EventDAO
import com.example.tp3desenvolvimentokotlin.domain.entity.Event
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class EventDAOImpl: EventDAO {

    var db = FirebaseFirestore.getInstance().collection("event")

    override fun store(obj: Event): Task<Void> {
        return db.document().set(obj)
    }

    override fun destroy(id: String): Task<Void> {
        return db.document(id).delete()
    }

    override fun update(obj: Event, key: String): Task<Void> {
        return db.document(key).set(obj)
    }

    override fun findBy(key: String): Task<QuerySnapshot> {
        return db.whereEqualTo("useId", key).get();
    }

    override fun findAll(): Task<QuerySnapshot> {
        throw NotImplementedError()
    }
}