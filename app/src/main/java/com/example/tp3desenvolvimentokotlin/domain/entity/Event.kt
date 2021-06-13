package com.example.tp3desenvolvimentokotlin.domain.entity

import com.google.firebase.firestore.DocumentId
import com.google.type.DateTime
import java.util.*

class Event(
    @DocumentId
    var id: String ?= null,
    var useId: String ?= null,
    var nextVaccine: String ?= null,
    var name: String ?= null,
    var total: Int ?= null,
    var releases: List<Release> ?= null
)