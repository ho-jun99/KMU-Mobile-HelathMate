package com.example.midterm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserData (
    var userID : String,
    var userPW : String,
    var userName : String,
    var userPN : String,
    var userAddress : String
    ){
    @PrimaryKey(autoGenerate = true) var id : Int =0
}




