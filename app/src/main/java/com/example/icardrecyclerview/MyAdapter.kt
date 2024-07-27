package com.example.icardrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private var context: Context, private var data: ArrayList<MyModel>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name:TextView = view.findViewById(R.id.name_tv)
        val enroll:TextView = view.findViewById(R.id.enroll_tv)
        val department:TextView = view.findViewById(R.id.department_tv)
        val institute:TextView = view.findViewById(R.id.institute_tv)
        val profile: ImageView = view.findViewById(R.id.profile_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_item,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = data.get(position).name
        holder.enroll.text = data[position].enroll
        holder.institute.text = data[position].institute
        holder.department.text = data[position].department
        holder.profile.setImageResource(data[position].profile)
    }

}