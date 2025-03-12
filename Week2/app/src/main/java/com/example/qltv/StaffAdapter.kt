package com.example.qltv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StaffAdapter(
    private var users: List<User>,
    private val onItemClick: (User) -> Unit
) : RecyclerView.Adapter<StaffAdapter.StaffViewHolder>() {

    fun updateUsers(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }

    class StaffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvStaffName: TextView = itemView.findViewById(R.id.tvStaffName)
        val btnSelect: Button = itemView.findViewById(R.id.btnSelectStaff)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_staff, parent, false)
        return StaffViewHolder(view)
    }

    override fun onBindViewHolder(holder: StaffViewHolder, position: Int) {
        val user = users[position]
        holder.tvStaffName.text = user.name
        holder.btnSelect.setOnClickListener {
            onItemClick(user)
        }
    }

    override fun getItemCount(): Int = users.size
}
