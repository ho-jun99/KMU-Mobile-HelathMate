package com.example.midterm

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        val editor = sharedPreferences.edit();
        editor.putBoolean("isLogin",false);
        editor.apply();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val SignupIntent = Intent(this,SignupActivity::class.java)
        val ShopIntent = Intent(this,ShopActivity::class.java)
        val btn_signup : Button = findViewById(R.id.btn_signup);
        val btn_login : Button = findViewById(R.id.btn_login);
        val btn_guestShop : Button = findViewById(R.id.btn_guestShop);

        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        val editor = sharedPreferences.edit();
        editor.putBoolean("isLogin",false);
        editor.apply();


        btn_login.setOnClickListener{
            val inputID : TextView = findViewById(R.id.text_ID);
            val inputPW : TextView = findViewById(R.id.text_PW);

            val id = sharedPreferences.getString("id",null);
            val pw = sharedPreferences.getString("pw",null);

            Log.e("tag",id.toString());
            Log.e("tag",pw.toString());
            if( id == null || pw == null){
                Toast.makeText(this, "저장된 로그인 값이 없습니다.", Toast.LENGTH_SHORT).show()
            }else if (inputID.text.toString() == id && inputPW.text.toString() == pw){
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                editor.putBoolean("isLogin",true);
                editor.apply();
                startActivity(ShopIntent);
            }else{
                Toast.makeText(this, "아이디 또는 비밀번호를 확인해주세요 ", Toast.LENGTH_SHORT).show()
            }
        }

        btn_signup.setOnClickListener{
            startActivity(SignupIntent)
        }

        btn_guestShop.setOnClickListener{
            editor.putBoolean("isLogin",false);
            editor.apply();
            Toast.makeText(this, "비회원입니다", Toast.LENGTH_SHORT).show()

            startActivity(ShopIntent)
        }


    }





}