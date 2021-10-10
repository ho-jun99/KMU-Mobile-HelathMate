package com.example.midterm

import android.service.autofill.UserData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface UserDataDAO {
    @Insert
    fun insert(userData: com.example.midterm.UserData)

    @Update
    fun updata(userData : com.example.midterm.UserData)

    @Query("SELECT * FROM UserData")
    fun getUserAll() : List<com.example.midterm.UserData>

    @Query("SELECT * FROM UserData WHERE userID = :userID")
    fun getUserByUserID(userID : String) : com.example.midterm.UserData
}