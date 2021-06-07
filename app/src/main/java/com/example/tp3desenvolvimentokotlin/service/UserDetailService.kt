package com.example.tp3desenvolvimentokotlin.service

import com.example.tp3desenvolvimentokotlin.domain.entity.UserDetail
import com.google.android.gms.tasks.Task

interface UserDetailService {
    fun register(userDetail: UserDetail): Task<Void>;
}