package com.example.bai1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SinhVienAdapter(
    private val sinhVienList: List<SinhVien>,
    private val onItemClick: (Int) -> Unit,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<SinhVienAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.nameText)
        val idText: TextView = itemView.findViewById(R.id.idText)
        val btnRemove: ImageView = itemView.findViewById(R.id.btnRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sinhvien, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = sinhVienList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sv = sinhVienList[position]

        holder.nameText.text = sv.hoTen
        holder.idText.text = sv.mssv

        // Click chọn sinh viên
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }

        // Click xóa sinh viên
        holder.btnRemove.setOnClickListener {
            onDeleteClick(position)
        }
    }
}
