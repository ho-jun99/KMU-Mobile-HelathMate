package com.example.midterm

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserData::class], version = 1)
abstract class UserDataDB :RoomDatabase(){
    abstract fun userDataDAO() : UserDataDAO
}