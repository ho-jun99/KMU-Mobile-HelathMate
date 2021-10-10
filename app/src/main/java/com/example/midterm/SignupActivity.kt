package com.example.midterm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.room.Room
import org.w3c.dom.Text

class SignupActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val txt_userID : TextView = findViewById(R.id.edit_userID)
        val txt_checkPW : TextView = findViewById(R.id.edit_checkPW)
        val txt_userPW : TextView = findViewById(R.id.edit_userPW)
        val txt_userName : TextView = findViewById(R.id.edit_name)
        val txt_PhoneNumber : TextView = findViewById(R.id.edit_PhoneNumber)
        val txt_Address : TextView = findViewById(R.id.edit_address)
        val radio_groupAccept : RadioGroup = findViewById(R.id.radio_groupAccept)
        val radio_Accept : RadioButton = findViewById(R.id.radio_Accept)
        val radio_Decline : RadioButton = findViewById(R.id.radio_Decline)

        val btn_submit :Button = findViewById(R.id.btn_submitSign)


        val db = Room.databaseBuilder(
            applicationContext,
            UserDataDB::class.java, "UserData" // DB 이름
        ).allowMainThreadQueries().build()

        btn_submit.setOnClickListener{


            if(txt_userID.text.toString().length == 0 || txt_checkPW.text.toString().length == 0 || txt_userPW.text.toString().length == 0 ||
                    txt_userName.text.toString().length == 0 || txt_PhoneNumber.text.toString().length == 0 || txt_Address.text.toString().length == 0){
                Toast.makeText(this,"모든 정보를 입력해 주세요.",Toast.LENGTH_LONG).show()
            }else if (!radio_Accept.isChecked){
                Toast.makeText(this,"약관에 동의해 주세요.",Toast.LENGTH_LONG).show()
            }
            else if(txt_checkPW.text.toString() != txt_userPW.text.toString()){
                Toast.makeText(this,"비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this,"회원가입 성공!",Toast.LENGTH_LONG).show()
                val newUser = UserData(txt_userID.text.toString(),txt_userPW.text.toString(),txt_userName.text.toString(), txt_PhoneNumber.text.toString(), txt_Address.text.toString())
                db.userDataDAO().insert(newUser)
                println(newUser)
            }


        }
    }


}