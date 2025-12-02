# Ứng dụng Quản lý Sinh viên - Android

Ứng dụng quản lý danh sách sinh viên với các chức năng thêm, xem chi tiết, cập nhật và xóa sinh viên.

## Mô tả

Ứng dụng được xây dựng với các Activity sau:

### 1. MainActivity
- Hiển thị danh sách sinh viên (MSSV và Họ tên)
- Có Option Menu để mở màn hình thêm sinh viên
- Click vào sinh viên để xem chi tiết
- Có nút xóa sinh viên

### 2. AddStudentActivity
- Màn hình thêm sinh viên mới
- Nhập đầy đủ thông tin: MSSV, Họ tên, Số điện thoại, Địa chỉ
- Validate dữ liệu đầu vào
- Kiểm tra MSSV trùng

### 3. StudentDetailActivity
- Hiển thị chi tiết thông tin sinh viên
- Cho phép cập nhật thông tin (trừ MSSV)
- MSSV không được phép chỉnh sửa

## Cấu trúc dữ liệu

```kotlin
data class SinhVien(
    val mssv: String,        // Mã số sinh viên
    var hoTen: String,       // Họ và tên
    var soDienThoai: String, // Số điện thoại
    var diaChi: String       // Địa chỉ
) : Parcelable
```

## Tính năng

✅ Xem danh sách sinh viên  
✅ Thêm sinh viên mới qua Option Menu  
✅ Xem chi tiết sinh viên (click vào item)  
✅ Cập nhật thông tin sinh viên  
✅ Xóa sinh viên  
✅ Validate dữ liệu đầu vào  
✅ Kiểm tra MSSV trùng lặp  

## Hướng dẫn sử dụng

1. **Xem danh sách**: Mở app sẽ hiển thị danh sách sinh viên mẫu
2. **Thêm sinh viên**: 
   - Nhấn vào icon "+" trên thanh menu
   - Nhập đầy đủ thông tin
   - Nhấn "Lưu"
3. **Xem chi tiết/Cập nhật**: 
   - Click vào sinh viên trong danh sách
   - Xem và chỉnh sửa thông tin
   - Nhấn "Cập nhật" để lưu
4. **Xóa sinh viên**: Nhấn vào icon thùng rác bên phải mỗi sinh viên

## Công nghệ sử dụng

- **Language**: Kotlin
- **UI**: XML Layout với Material Design
- **Components**: 
  - RecyclerView
  - CardView
  - TextInputLayout
  - Option Menu
  - Activity Result API
  - Parcelable

## Build & Run

1. Mở project trong Android Studio
2. Sync Gradle
3. Chạy trên emulator hoặc thiết bị thật (Android 7.0+)
