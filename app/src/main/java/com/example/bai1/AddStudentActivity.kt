package com.example.bai1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {

    private lateinit var edtMssv: EditText
    private lateinit var edtHoTen: EditText
    private lateinit var edtSoDienThoai: EditText
    private lateinit var edtDiaChi: EditText
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        // Setup ActionBar
        supportActionBar?.title = "Thêm sinh viên mới"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Ánh xạ view
        edtMssv = findViewById(R.id.edtMssv)
        edtHoTen = findViewById(R.id.edtHoTen)
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai)
        edtDiaChi = findViewById(R.id.edtDiaChi)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)

        // Xử lý sự kiện
        btnSave.setOnClickListener {
            saveStudent()
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun saveStudent() {
        val mssv = edtMssv.text.toString().trim()
        val hoTen = edtHoTen.text.toString().trim()
        val soDienThoai = edtSoDienThoai.text.toString().trim()
        val diaChi = edtDiaChi.text.toString().trim()

        // Validate
        if (mssv.isEmpty()) {
            edtMssv.error = "Vui lòng nhập MSSV"
            edtMssv.requestFocus()
            return
        }

        if (hoTen.isEmpty()) {
            edtHoTen.error = "Vui lòng nhập họ tên"
            edtHoTen.requestFocus()
            return
        }

        if (soDienThoai.isEmpty()) {
            edtSoDienThoai.error = "Vui lòng nhập số điện thoại"
            edtSoDienThoai.requestFocus()
            return
        }

        if (diaChi.isEmpty()) {
            edtDiaChi.error = "Vui lòng nhập địa chỉ"
            edtDiaChi.requestFocus()
            return
        }

        // Tạo sinh viên mới
        val sinhVien = SinhVien(mssv, hoTen, soDienThoai, diaChi)

        // Trả về kết quả
        val intent = Intent()
        intent.putExtra("sinhvien", sinhVien)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
