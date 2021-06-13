package com.example.tp3desenvolvimentokotlin.service

import com.example.tp3desenvolvimentokotlin.service.dto.InputText
import com.example.tp3desenvolvimentokotlin.service.dto.UiMessageError
import java.util.*

interface ValidationService {
    fun validate(inputs: List<InputText>): ArrayList<UiMessageError>?
}