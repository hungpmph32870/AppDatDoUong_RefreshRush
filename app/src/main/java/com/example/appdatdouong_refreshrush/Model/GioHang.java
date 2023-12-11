package com.example.appdatdouong_refreshrush.Model;

public class GioHang {
    private int maGio;
    private int soLuong;
    private String diaChiGio;
    private int idDoUong;
    private String maKh;

    public int getMaGio() {
        return maGio;
    }

    public void setMaGio(int maGio) {
        this.maGio = maGio;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDiaChiGio() {
        return diaChiGio;
    }

    public void setDiaChiGio(String diaChiGio) {
        this.diaChiGio = diaChiGio;
    }

    public int getIdDoUong() {
        return idDoUong;
    }

    public void setIdDoUong(int idDoUong) {
        this.idDoUong = idDoUong;
    }

    public String getMaKh() {
        return maKh;
    }

    public void setMaKh(String maKh) {
        this.maKh = maKh;
    }

    public GioHang() {
    }

    public GioHang(int maGio, int soLuong, String diaChiGio, int idDoUong, String maKh) {
        this.maGio = maGio;
        this.soLuong = soLuong;
        this.diaChiGio = diaChiGio;
        this.idDoUong = idDoUong;
        this.maKh = maKh;
    }

    public GioHang(int soLuong, String diaChiGio, int idDoUong, String maKh) {
        this.soLuong = soLuong;
        this.diaChiGio = diaChiGio;
        this.idDoUong = idDoUong;
        this.maKh = maKh;
    }
}
