package com.example.midterm

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midterm.databinding.ActivityShopBinding
import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback

import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult

import androidx.activity.result.ActivityResultLauncher
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.*
import androidx.core.net.toUri

var myUri ="";


class ShopActivity : AppCompatActivity(){
    private lateinit var binding: ActivityShopBinding

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && data!=null){
            if(requestCode == 200) {
                var imageUri : Uri? = data?.data


                myUri = imageUri.toString()

                Toast.makeText(this, "사진이 선택 됐습니다.", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //뷰 바인딩
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profileList = arrayListOf(
            Profiles(Uri.parse("android.resource://com.example.midterm/drawable/hojun"),"김호준"),
            Profiles(Uri.parse("android.resource://com.example.midterm/drawable/hwang"),"황철순"),
            Profiles(Uri.parse("android.resource://com.example.midterm/drawable/kangmin"),"김강민"),
            Profiles(Uri.parse("android.resource://com.example.midterm/drawable/minsu"),"김민수"),
            Profiles(Uri.parse("android.resource://com.example.midterm/drawable/colmon"),"로니콜먼"),
        )

        //리사이클 뷰
        binding.rvProfile.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        binding.rvProfile.setHasFixedSize(true)
        binding.rvProfile.adapter = ProfileAdapter(profileList)


        //메이트 추가 다이얼로
        binding.btnAddMate.setOnClickListener {
            myUri =""
            val dialogView = LayoutInflater.from(this).inflate(R.layout.select_gallery,null);
            val myBuilder = AlertDialog.Builder(this)
                .setView(dialogView)
                .create()

            val txt_addName = dialogView.findViewById<EditText>(R.id.txt_addName)
            val btn_post = dialogView.findViewById<Button>(R.id.btn_Post)
            val btn_getImage = dialogView.findViewById<Button>(R.id.btn_getImage)

            btn_getImage.setOnClickListener {
                var intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(intent,200)
            }

            btn_post.setOnClickListener {
                if(txt_addName.text.toString().length == 0 ) {
                    Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
                }else if(myUri.equals("")){
                    Toast.makeText(this, "사진을 선택해 주세요", Toast.LENGTH_SHORT).show()
                }else {
                    profileList.add(Profiles(myUri.toUri(),txt_addName.text.toString()))
                    binding.rvProfile.adapter = ProfileAdapter(profileList)
                    myBuilder.dismiss()
                }

            }

            myBuilder.show()

        }


        val pref = getSharedPreferences("user",0);


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
                val btnCloseInfo = loginUserDialogView.findViewById<Button>(R.id.btn_closeInfo)

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
                    .setTitle("회원 정보")
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