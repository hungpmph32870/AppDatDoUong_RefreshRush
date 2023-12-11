package com.example.appdatdouong_refreshrush.Model;

public class NguoiDung {
    private String idNguoiDung,hoTen,tenTaiKhoan, email, soDienThoai, matKhau;
    public String toString() {
        return "NGUOIDUNG{" +
                "IDNGUOIDUNG='" + idNguoiDung + '\'' +
                ", HOTEN='" + hoTen + '\'' +
                ", TENTAIKHOAN='" + tenTaiKhoan + '\'' +
                ", EMAIL=" + email +
                ", SODIENTHOAI='" + soDienThoai + '\'' +
                ", MATKHAU='" + matKhau + '\'' +
                '}';
    }

    public NguoiDung() {
    }

    public NguoiDung(String idNguoiDung, String hoTen, String tenTaiKhoan, String email, String soDienThoai, String matKhau) {
        this.idNguoiDung = idNguoiDung;
        this.hoTen = hoTen;
        this.tenTaiKhoan = tenTaiKhoan;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.matKhau = matKhau;
    }

    public String getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(String idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
