package com.example.bai1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StudentDetailActivity : AppCompatActivity() {

    private lateinit var edtMssv: EditText
    private lateinit var edtHoTen: EditText
    private lateinit var edtSoDienThoai: EditText
    private lateinit var edtDiaChi: EditText
    private lateinit var btnUpdate: Button
    private lateinit var btnCancel: Button

    private var sinhVien: SinhVien? = null
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        // Setup ActionBar
        supportActionBar?.title = "Chi tiết sinh viên"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Ánh xạ view
        edtMssv = findViewById(R.id.edtMssv)
        edtHoTen = findViewById(R.id.edtHoTen)
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai)
        edtDiaChi = findViewById(R.id.edtDiaChi)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnCancel = findViewById(R.id.btnCancel)

        // Nhận dữ liệu từ intent
        sinhVien = intent.getParcelableExtra("sinhvien")
        position = intent.getIntExtra("position", -1)

        // Hiển thị thông tin
        sinhVien?.let {
            edtMssv.setText(it.mssv)
            edtHoTen.setText(it.hoTen)
            edtSoDienThoai.setText(it.soDienThoai)
            edtDiaChi.setText(it.diaChi)
            
            // MSSV không được phép sửa
            edtMssv.isEnabled = false
        }

        // Xử lý sự kiện
        btnUpdate.setOnClickListener {
            updateStudent()
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun updateStudent() {
        val hoTen = edtHoTen.text.toString().trim()
        val soDienThoai = edtSoDienThoai.text.toString().trim()
        val diaChi = edtDiaChi.text.toString().trim()

        // Validate
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

        // Cập nhật thông tin
        sinhVien?.let {
            it.hoTen = hoTen
            it.soDienThoai = soDienThoai
            it.diaChi = diaChi

            // Trả về kết quả
            val intent = Intent()
            intent.putExtra("sinhvien", it)
            intent.putExtra("position", position)
            setResult(Activity.RESULT_OK, intent)
            
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
