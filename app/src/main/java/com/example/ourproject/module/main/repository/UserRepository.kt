package com.example.ourproject.module.main.repository

import com.example.ourproject.core.database.UserDao
import com.example.ourproject.core.datastore.DataStoreManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val dataStoreManager: DataStoreManager
) {
    fun getUser() = userDao.getUser()

    fun getTokenFlow() = dataStoreManager.getDataStore().data

}