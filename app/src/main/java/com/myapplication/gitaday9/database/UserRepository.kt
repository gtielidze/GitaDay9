package com.myapplication.gitaday9.database

import androidx.lifecycle.LiveData
import com.myapplication.gitaday9.User

class UserRepository() {

    private lateinit var userDao: UserDao
    val readAllData: List<User> = userDao.getAll()

//    fun addUser(user: User) {
//        userDao.insert(user)
//    }
}