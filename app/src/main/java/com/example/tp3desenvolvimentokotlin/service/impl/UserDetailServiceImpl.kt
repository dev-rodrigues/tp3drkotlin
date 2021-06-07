package com.example.tp3desenvolvimentokotlin.service.impl

import com.example.tp3desenvolvimentokotlin.domain.dao.impl.UserDetailDAOImpl
import com.example.tp3desenvolvimentokotlin.domain.entity.UserDetail
import com.example.tp3desenvolvimentokotlin.service.UserDetailService
import com.google.android.gms.tasks.Task

class UserDetailServiceImpl(): UserDetailService {

    private var userDetailDAO: UserDetailDAOImpl = UserDetailDAOImpl()

    override fun register(userDetail: UserDetail): Task<Void> {
        return userDetailDAO.store(userDetail)
    }
}