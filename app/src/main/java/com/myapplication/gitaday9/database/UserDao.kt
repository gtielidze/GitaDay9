package com.myapplication.gitaday9.database

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myapplication.gitaday9.User


@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getAll(): List<User>

       //@Query("SELECT * FROM user_table WHERE mobile_number LIKE :number LIMIT 1")
    @Query("SELECT EXISTS(SELECT 1 FROM user_table WHERE mobile_number = :number)")
    suspend fun findNumber(number: String): Boolean

    @Query("SELECT * FROM user_table WHERE mobile_number=:number")
    suspend fun getUser(number: String?): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

}