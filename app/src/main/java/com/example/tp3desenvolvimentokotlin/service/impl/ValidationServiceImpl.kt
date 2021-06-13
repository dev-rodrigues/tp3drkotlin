package com.example.tp3desenvolvimentokotlin.service.impl

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.tp3desenvolvimentokotlin.service.ValidationService
import com.example.tp3desenvolvimentokotlin.service.dto.InputText
import com.example.tp3desenvolvimentokotlin.service.dto.TypeError
import com.example.tp3desenvolvimentokotlin.service.dto.UiMessageError
import java.util.*
import java.util.Optional.empty
import kotlin.collections.ArrayList

class ValidationServiceImpl: ValidationService {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun validate(inputs: List<InputText>): ArrayList<UiMessageError>? {
        var messages = ArrayList<UiMessageError>()

        inputs.forEach {
            if (it.message.isNullOrBlank()) {
                messages.add(UiMessageError(it.componente, "O campo ${it.componente} é obrigatório", TypeError.validation))
            }
        }
        return Optional.of(messages).orElse(null);
    }
}