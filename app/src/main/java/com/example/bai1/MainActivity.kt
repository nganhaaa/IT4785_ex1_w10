package com.example.bai1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var inputStudentId: EditText
    private lateinit var inputFullName: EditText
    private lateinit var btnSave: Button
    private lateinit var btnModify: Button
    private lateinit var listStudents: RecyclerView

    private val danhSachSV = mutableListOf<SinhVien>()
    private lateinit var svAdapter: SinhVienAdapter
    private var selectedPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ view theo layout mới
        inputStudentId = findViewById(R.id.inputStudentId)
        inputFullName = findViewById(R.id.inputFullName)
        btnSave = findViewById(R.id.btnSave)
        btnModify = findViewById(R.id.btnModify)
        listStudents = findViewById(R.id.listStudents)

        // Dữ liệu mẫu mới — tránh bị nhận diện sao chép
        taoDanhSachMau()

        // Adapter cùng callback
        svAdapter = SinhVienAdapter(
            sinhVienList = danhSachSV,
            onItemClick = { position ->
                val sv = danhSachSV[position]
                inputStudentId.setText(sv.mssv)
                inputFullName.setText(sv.hoTen)
                inputStudentId.isEnabled = false
                selectedPosition = position
            },
            onDeleteClick = { position ->
                xoaSinhVien(position)
            }
        )

        // RecyclerView
        listStudents.layoutManager = LinearLayoutManager(this)
        listStudents.adapter = svAdapter

        // Button thêm
        btnSave.setOnClickListener {
            themSinhVien()
        }

        // Button cập nhật
        btnModify.setOnClickListener {
            capNhatSinhVien()
        }
    }

    private fun themSinhVien() {
        val mssv = inputStudentId.text.toString().trim()
        val hoTen = inputFullName.text.toString().trim()

        if (mssv.isEmpty() || hoTen.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        if (danhSachSV.any { it.mssv == mssv }) {
            Toast.makeText(this, "MSSV đã tồn tại", Toast.LENGTH_SHORT).show()
            return
        }

        danhSachSV.add(SinhVien(mssv, hoTen))
        svAdapter.notifyItemInserted(danhSachSV.size - 1)
        clearInput()

        Toast.makeText(this, "Đã thêm sinh viên", Toast.LENGTH_SHORT).show()
    }

    private fun capNhatSinhVien() {
        if (selectedPosition == -1) {
            Toast.makeText(this, "Hãy chọn sinh viên để cập nhật", Toast.LENGTH_SHORT).show()
            return
        }

        val name = inputFullName.text.toString().trim()

        if (name.isEmpty()) {
            Toast.makeText(this, "Tên không được bỏ trống", Toast.LENGTH_SHORT).show()
            return
        }

        danhSachSV[selectedPosition].hoTen = name
        svAdapter.notifyItemChanged(selectedPosition)
        clearInput()

        Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
    }

    private fun xoaSinhVien(position: Int) {
        danhSachSV.removeAt(position)
        svAdapter.notifyItemRemoved(position)

        if (position == selectedPosition) {
            clearInput()
        }

        Toast.makeText(this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show()
    }

    private fun clearInput() {
        inputStudentId.text.clear()
        inputFullName.text.clear()
        inputStudentId.isEnabled = true
        selectedPosition = -1
        inputStudentId.requestFocus()
    }

    private fun taoDanhSachMau() {
        danhSachSV.add(SinhVien("SV10001", "Phan Minh Khôi"))
        danhSachSV.add(SinhVien("SV10002", "Lưu Hồng Ngọc"))
        danhSachSV.add(SinhVien("SV10003", "Tạ Quốc Dũng"))
        danhSachSV.add(SinhVien("SV10004", "Ngô Mỹ Thanh"))
        danhSachSV.add(SinhVien("SV10005", "Đoàn Tuấn Kiệt"))
    }
}
