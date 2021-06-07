package com.example.tp3desenvolvimentokotlin.domain.dao

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface DAO<T, K> {
    fun store(obj: T): Task<Void>;
    fun destroy(type: K): Task<Void>;
    fun update(obj: T, key: K): Task<Void>;

    fun findBy(key: K): Task<QuerySnapshot>;
    fun findAll(): Task<QuerySnapshot>;
}