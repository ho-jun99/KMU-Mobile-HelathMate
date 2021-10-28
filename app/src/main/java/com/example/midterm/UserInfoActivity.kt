package com.example.midterm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.midterm.databinding.ActivityShopBinding
import com.example.midterm.databinding.ActivityUserinfoBinding

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserinfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserinfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);


        binding.txtNameInfo.setText(sharedPreferences.getString("name",null))
        binding.txtIdInfo.setText(sharedPreferences.getString("id",null));
        binding.txtAddrInfo.setText(sharedPreferences.getString("address",null));
        binding.txtPhoneInfo.setText(sharedPreferences.getString("phoneNumber",null));


    }
}