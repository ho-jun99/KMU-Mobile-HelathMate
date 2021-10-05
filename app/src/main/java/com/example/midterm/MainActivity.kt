package com.example.midterm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val SignupIntent = Intent(this,SignupActivity::class.java)
        val btn_signup : Button = findViewById(R.id.btn_signup)

        btn_signup.setOnClickListener{
            startActivity(SignupIntent)
        }


    }





}