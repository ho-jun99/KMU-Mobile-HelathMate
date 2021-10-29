package com.example.midterm

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter(val profileList : ArrayList<Profiles>) : RecyclerView.Adapter<ProfileAdapter.CustomViewHolder>(){

    var mPosition = 0;

    fun getPosition(): Int {
        return mPosition
    }

    fun setPosition(position: Int){
        mPosition = position
    }

    fun addItem(profiles: Profiles){
        profileList.add(profiles)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        if (profileList.size > 0 ) {
            profileList.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)

        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileAdapter.CustomViewHolder, position: Int) {
        holder.myImg.setImageURI(profileList.get(position).image)
        holder.name.text = profileList.get(position).name

        //삭제 버튼
        holder.btn_delete.setOnClickListener {
            if(profileList.size <= 1 ) {

            }else {
                Log.e("FIND : ",profileList[0].image.toString())
                removeItem(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myImg = itemView.findViewById<ImageView>(R.id.iv_profile) //이미지
        val name = itemView.findViewById<TextView>(R.id.id_profileName) //이름
        val btn_delete = itemView.findViewById<Button>(R.id.btn_delete) //삭제버튼
    }

}