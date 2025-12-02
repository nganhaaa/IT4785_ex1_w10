package com.example.bai1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var listStudents: RecyclerView
    private val danhSachSV = mutableListOf<SinhVien>()
    private lateinit var svAdapter: SinhVienAdapter

    // Activity result launcher để thêm sinh viên
    private val addStudentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val sinhVien = result.data?.getParcelableExtra<SinhVien>("sinhvien")
            sinhVien?.let {
                // Kiểm tra MSSV trùng
                if (danhSachSV.any { sv -> sv.mssv == it.mssv }) {
                    Toast.makeText(this, "MSSV đã tồn tại", Toast.LENGTH_SHORT).show()
                    return@registerForActivityResult
                }
                
                danhSachSV.add(it)
                svAdapter.notifyItemInserted(danhSachSV.size - 1)
                Toast.makeText(this, "Đã thêm sinh viên", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Activity result launcher để cập nhật sinh viên
    private val updateStudentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val sinhVien = result.data?.getParcelableExtra<SinhVien>("sinhvien")
            val position = result.data?.getIntExtra("position", -1) ?: -1
            
            if (sinhVien != null && position != -1 && position < danhSachSV.size) {
                danhSachSV[position] = sinhVien
                svAdapter.notifyItemChanged(position)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup ActionBar
        supportActionBar?.title = "Danh sách sinh viên"

        // Ánh xạ view
        listStudents = findViewById(R.id.listStudents)

        // Dữ liệu mẫu
        taoDanhSachMau()

        // Adapter với callback
        svAdapter = SinhVienAdapter(
            sinhVienList = danhSachSV,
            onItemClick = { position ->
                // Mở activity chi tiết sinh viên
                openStudentDetail(position)
            },
            onDeleteClick = { position ->
                xoaSinhVien(position)
            }
        )

        // RecyclerView
        listStudents.layoutManager = LinearLayoutManager(this)
        listStudents.adapter = svAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add_student -> {
                openAddStudent()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openAddStudent() {
        val intent = Intent(this, AddStudentActivity::class.java)
        addStudentLauncher.launch(intent)
    }

    private fun openStudentDetail(position: Int) {
        val intent = Intent(this, StudentDetailActivity::class.java)
        intent.putExtra("sinhvien", danhSachSV[position])
        intent.putExtra("position", position)
        updateStudentLauncher.launch(intent)
    }

    private fun xoaSinhVien(position: Int) {
        danhSachSV.removeAt(position)
        svAdapter.notifyItemRemoved(position)
        Toast.makeText(this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show()
    }

    private fun taoDanhSachMau() {
        danhSachSV.add(SinhVien("SV10001", "Phan Minh Khôi", "0912345678", "Hà Nội"))
        danhSachSV.add(SinhVien("SV10002", "Lưu Hồng Ngọc", "0923456789", "Hải Phòng"))
        danhSachSV.add(SinhVien("SV10003", "Tạ Quốc Dũng", "0934567890", "Đà Nẵng"))
        danhSachSV.add(SinhVien("SV10004", "Ngô Mỹ Thanh", "0945678901", "TP HCM"))
        danhSachSV.add(SinhVien("SV10005", "Đoàn Tuấn Kiệt", "0956789012", "Cần Thơ"))
    }
}
