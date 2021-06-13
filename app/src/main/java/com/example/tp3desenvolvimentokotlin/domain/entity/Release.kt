package com.example.tp3desenvolvimentokotlin.domain.entity

import com.google.firebase.firestore.DocumentId
import java.util.*

class Release (
    @DocumentId
    var id: String ?= null,
    var date: Date?= null,
)