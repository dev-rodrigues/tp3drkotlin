package com.example.tp3desenvolvimentokotlin.domain.entity

import com.google.firebase.firestore.DocumentId

class UserDetail (
    @DocumentId
    var id: String ?= null,
    var emailKey: String ?= null,
    var fullName: String ?= null
)