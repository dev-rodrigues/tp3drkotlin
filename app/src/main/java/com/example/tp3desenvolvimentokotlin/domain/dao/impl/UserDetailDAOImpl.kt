package com.example.tp3desenvolvimentokotlin.domain.dao.impl

import com.example.tp3desenvolvimentokotlin.domain.dao.UserDetailDAO
import com.example.tp3desenvolvimentokotlin.domain.entity.UserDetail
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class UserDetailDAOImpl: UserDetailDAO {

    var db = FirebaseFirestore.getInstance().collection("userdetail")

    override fun store(obj: UserDetail): Task<Void> {
        return db.document().set(obj)
    }

    override fun destroy(key: String): Task<Void> {
        throw NotImplementedError()
    }

    override fun update(obj: UserDetail, key: String): Task<Void> {
        throw NotImplementedError()
    }

    override fun findBy(key: String): Task<QuerySnapshot> {
        return db.whereEqualTo("emailKey", key).get()
    }

    override fun findAll(): Task<QuerySnapshot> {
        throw NotImplementedError()
    }
}