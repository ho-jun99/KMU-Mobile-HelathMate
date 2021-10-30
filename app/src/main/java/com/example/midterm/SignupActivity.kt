package com.example.midterm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.room.Room
import org.w3c.dom.Text

class SignupActivity : AppCompatActivity() {


    // 핸드폰번식 형식검사 함수
    fun checkPhoneNumber(number : String) : Boolean {
        if(number.length == 13) {
            for(i in 0..12){
                if(i == 3){
                    Log.e("PHONE_NUMBER",number[i].toString())
                    if(!number[i].toString().equals("-")){
                        return false
                    }
                }
                if( i== 8){
                    Log.e("PHONE_NUMBER",number[i].toString())
                    if(!number[i].toString().equals("-")){
                        return false
                    }
                }
            }
            return true
        }
        return false
    }

    //정규식 (숫자,문자,특수문자중, 2가지 포함 6~12)
    fun isPasswordFormat(password: String): Boolean {
        return !password.matches("^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{6,12}\$".toRegex())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val txt_userID : TextView = findViewById(R.id.edit_userID)
        val txt_checkPW : TextView = findViewById(R.id.edit_checkPW)
        val txt_userPW : TextView = findViewById(R.id.edit_userPW)
        val txt_userName : TextView = findViewById(R.id.edit_name)
        val txt_PhoneNumber : TextView = findViewById(R.id.edit_PhoneNumber)
        val txt_Address : TextView = findViewById(R.id.edit_address)
        val radio_Accept : RadioButton = findViewById(R.id.radio_Accept)
        val btn_checkID : Button = findViewById(R.id.btn_checkID);
        val btn_submit :Button = findViewById(R.id.btn_submitSign)

        var isIdChecked = false;

        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        val editor = sharedPreferences.edit();
        val myID = sharedPreferences.getString("id",null)
            
        btn_checkID.setOnClickListener {

            if(myID == txt_userID.text.toString()){
                isIdChecked = false;
                Toast.makeText(this, "다른 아이디를 사용해주세요.", Toast.LENGTH_SHORT).show()
            }else if(txt_userID.text.toString().length == 0){
                isIdChecked = false;
                Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
            }else {
                isIdChecked = true;
                Toast.makeText(this, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
            }
        }


        btn_submit.setOnClickListener{
            if(txt_userID.text.toString().length == 0 || txt_checkPW.text.toString().length == 0 || txt_userPW.text.toString().length == 0 ||
                    txt_userName.text.toString().length == 0 || txt_PhoneNumber.text.toString().length == 0 || txt_Address.text.toString().length == 0){
                Toast.makeText(this,"모든 정보를 입력해 주세요.",Toast.LENGTH_SHORT).show()
            }else if (!radio_Accept.isChecked){
                Toast.makeText(this,"약관에 동의해 주세요.",Toast.LENGTH_SHORT).show()
            }else if(txt_checkPW.text.toString() != txt_userPW.text.toString()){
                Toast.makeText(this,"비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show()
            }else if (!isIdChecked){
                Toast.makeText(this, "아이디 중복 검사를 완료해 주세요.", Toast.LENGTH_SHORT).show()
            }else if(!checkPhoneNumber(txt_PhoneNumber.text.toString())){
                Toast.makeText(this,"xxx-xxxx-xxxx형식에 맞게 입력해주세요.",Toast.LENGTH_SHORT).show()
            }else if (isPasswordFormat(txt_userPW.text.toString())){
                Toast.makeText(this, "6~12자리, 숫자,문자,특수문자를 포함시켜주세요", Toast.LENGTH_SHORT).show()
            }else{
                if(myID != txt_userID.text.toString()){
                    editor.putString("id",txt_userID.text.toString()); // 아이디
                    editor.putString("pw",txt_userPW.text.toString()); // 비밀번호
                    editor.putString("phoneNumber",txt_PhoneNumber.text.toString());
                    editor.putString("address",txt_Address.text.toString());
                    editor.putString("name",txt_userName.text.toString());
                    editor.putBoolean("isLogin",false);
                    editor.apply();
                    Toast.makeText(this,"회원가입 성공!",Toast.LENGTH_SHORT).show()
                    finish();
                }else {
                    Toast.makeText(this,"아이디 중복검사를 다시 해주세요.",Toast.LENGTH_SHORT).show()
                    isIdChecked = false
                }
                

            }


        }
    }




}