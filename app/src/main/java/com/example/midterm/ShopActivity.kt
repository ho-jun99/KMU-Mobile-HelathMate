package com.example.midterm

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.midterm.databinding.ActivityShopBinding

class ShopActivity : AppCompatActivity(){

    private lateinit var binding: ActivityShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //뷰 바인딩
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = getSharedPreferences("user",0);

        binding.textIsLoginShop.text = pref.getBoolean("isLogin",false).toString()

        //회원정보 모달창
        if(pref.getBoolean("isLogin",false)){ //회원일
            binding.btnUserInfo.setOnClickListener {
                val loginUserDialogView = LayoutInflater.from(this).inflate(R.layout.activity_userinfo,null);
                val mBuilder = AlertDialog.Builder(this)
                    .setView(loginUserDialogView)
                    .create()


                val txtNameInfo = loginUserDialogView.findViewById<TextView>(R.id.txt_nameInfo)
                val txtIdinfo = loginUserDialogView.findViewById<TextView>(R.id.txt_idInfo)
                val txtAddrInfo = loginUserDialogView.findViewById<TextView>(R.id.txt_addrInfo)
                val txtPhoneInfo = loginUserDialogView.findViewById<TextView>(R.id.txt_phoneInfo)
                val btnCloseInfo = loginUserDialogView.findViewById<TextView>(R.id.btn_closeInfo)

                txtNameInfo.text = pref.getString("name",null);
                txtIdinfo.text = pref.getString("id",null);
                txtAddrInfo.text = pref.getString("address",null);
                txtPhoneInfo.text = pref.getString("phoneNumber",null);

                btnCloseInfo.setOnClickListener { mBuilder.dismiss() }

                mBuilder.show();
            }

        }else {
            val SignupIntent = Intent(this,SignupActivity::class.java)
            binding.btnUserInfo.setOnClickListener{
                val mBuilder = AlertDialog.Builder(this)
                    .setTitle("User-Info")
                    .setMessage("비로그인 상태입니다. 회원가입을 하시겠습니까?")
                    .setPositiveButton("회원가입",DialogInterface.OnClickListener { dialogInterface, i ->
                        Toast.makeText(this, "회원가입", Toast.LENGTH_SHORT).show()
                        startActivity(SignupIntent);
                        finish()
                    })
                    .setNegativeButton("닫기",DialogInterface.OnClickListener { dialogInterface, i ->
                        Toast.makeText(this, "닫기", Toast.LENGTH_SHORT).show()
                    })
                mBuilder.show();
            }


        }

    }
}