# Bài thực hành 02 – Lập trình Web

**Họ tên:** Cáp Thanh Nhàn  
**MSSV:** 23110276 

## Cấu trúc repo
- **BTTH02/**: Source code thực hiện tại lớp (Login) 
- **BTTH02_Vidu1_Vidu2/**: Source code hoàn thiện ở nhà (Login + Register) theo yêu cầu bài tập.  

# 📖 Giới thiệu

Bài thực hành số 02 môn Lập trình Web.
Ứng dụng minh họa mô hình MVC – kiến trúc 3 tầng (3-Tier Architecture) kết hợp JDBC để xây dựng chức năng đăng nhập và đăng ký tài khoản.

# ✨ Nội dung đã làm

Model

User.java: định nghĩa đối tượng User (id, email, username, fullname, password, roleid, phone, createdDate, …).

DAO

UserDao.java, UserDaoImpl.java: thao tác DB bằng JDBC (kiểm tra tồn tại, truy vấn User, insert User mới).

Service

UserService.java, UserServiceImpl.java: xử lý logic nghiệp vụ login, register, validate dữ liệu.

Controller

LoginController.java: xử lý đăng nhập (Session, Cookie, redirect).

RegisterController.java: xử lý đăng ký (validate, insert DB, điều hướng).

WaitingController.java: phân quyền và điều hướng sau login.

Utils

DBCon.java: quản lý kết nối Database (JDBC).

PasswordUtil.java: hỗ trợ xử lý mật khẩu.
