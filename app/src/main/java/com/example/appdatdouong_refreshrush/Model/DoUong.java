package com.example.appdatdouong_refreshrush.Model;

public class DoUong {
    private int idDoUong, giaDoUong, soLuong;
    private String tenDoUong, tinhTrang, diaChiQuan;

    public DoUong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public DoUong(int idDoUong, String tenDoUong, int giaDoUong,int soLuong, String tinhTrang , String diaChiQuan ) {
        this.idDoUong = idDoUong;
        this.tenDoUong = tenDoUong;
        this.giaDoUong = giaDoUong;
        this.soLuong = soLuong;
        this.tinhTrang = tinhTrang;
        this.diaChiQuan = diaChiQuan;

    }
    public DoUong(String tenDoUong, int giaDoUong, String tinhTrang, String diaChiQuan) {
        this.tenDoUong = tenDoUong;
        this.giaDoUong = giaDoUong;
        this.tinhTrang = tinhTrang;
        this.diaChiQuan = diaChiQuan;
    }

    public DoUong() {

    }


    public String getDiaChiQuan() {
        return diaChiQuan;
    }

    public void setDiaChiQuan(String diaChiQuan) {
        this.diaChiQuan = diaChiQuan;
    }

    public int getIdDoUong() {
        return idDoUong;
    }

    public void setIdDoUong(int idDoUong) {
        this.idDoUong = idDoUong;
    }

    public int getGiaDoUong() {
        return giaDoUong;
    }

    public void setGiaDoUong(int giaDoUong) {
        this.giaDoUong = giaDoUong;
    }

    public String getTenDoUong() {
        return tenDoUong;
    }

    public void setTenDoUong(String tenDoUong) {
        this.tenDoUong = tenDoUong;
    }

    

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
