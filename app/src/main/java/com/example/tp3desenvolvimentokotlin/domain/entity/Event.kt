package com.example.tp3desenvolvimentokotlin.domain.entity

import com.google.firebase.firestore.DocumentId
import com.google.type.DateTime

class Event(
    @DocumentId
    var id: String ?= null,
    var userDetail: UserDetail ?= null,
    var date: DateTime ?= null,
    var description: String ?= null
)