package com.myapplication.gitaday9

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    @ColumnInfo(name = "mobile_number")
    val mobileNumber: String?,
    @ColumnInfo(name = "password")
    val password: String?,
    @ColumnInfo(name = "name")
    val name: String?
)