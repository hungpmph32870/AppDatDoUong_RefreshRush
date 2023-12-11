package com.example.appdatdouong_refreshrush.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appdatdouong_refreshrush.DataBase.DbHelper;
import com.example.appdatdouong_refreshrush.Model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    private SQLiteDatabase db;


    public NguoiDungDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public int updatePass(NguoiDung obj){
//        if (checklogin(idNguoiDung, oldPass) > 0) {
//            // Old password is correct, update the password
//            ContentValues values = new ContentValues();
//            values.put("MATKHAU", newPass);
//
//            return db.update("NGUOIDUNG", values, "IDNGUOIDUNG= ?", new String[]{idNguoiDung});
//        } else {
//            // Old password is incorrect
//            return -1;
//        }
        ContentValues values = new ContentValues();
        values.put("TENTAIKHOAN", obj.getTenTaiKhoan());
        values.put("MATKHAU", obj.getMatKhau());
        return db.update("NGUOIDUNG", values, "IDNGUOIDUNG = ?", new String[]{String.valueOf(obj.getIdNguoiDung())});
    }
    public boolean isMaNvExists(String idNguoiDung) {
        String query = "SELECT * FROM NGUOIDUNG WHERE IDNGUOIDUNG = ?";
        Cursor cursor = db.rawQuery(query, new String[]{idNguoiDung});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public List<NguoiDung> getAll(){
        String sql = "SELECT * FROM NGUOIDUNG";
        return getData(sql);
    }
    public NguoiDung getID(String id) {
        String sql = "SELECT * FROM NGUOIDUNG WHERE maKh = ?";
        List<NguoiDung> list = getData(sql, id);
        return list.get(0);
    }

    public int checklogin(String tenTaiKhoan, String matKhau){
        String sql = "SELECT * FROM NGUOIDUNG WHERE TENTAIKHOAN = ? AND MATKHAU = ?";
        List<NguoiDung> list = getData(sql, tenTaiKhoan, matKhau);
        if (list.size() == 0){
            return - 1;
        }
        return 1;
//        String[] columns = {"IDNGUOIDUNG"};
//        String selection = "TENTAIKHOAN = ? AND MATKHAU = ?";
//        String[] selectionArgs = {tenTaiKhoan, matKhau};
//        Cursor cursor = db.query("NGUOIDUNG", columns, selection, selectionArgs, null, null, null);
//        int result = cursor.getCount();
//        cursor.close();
//        return result;
    }
    public long insert(NguoiDung nd){
        ContentValues values = new ContentValues();
        values.put("IDNGUOIDUNG", nd.getIdNguoiDung());
        values.put("HOTEN", nd.getHoTen());
        values.put("TENTAIKHOAN", nd.getTenTaiKhoan());
        values.put("EMAIL", nd.getEmail());
        values.put("SODIENTHOAI", nd.getSoDienThoai());
        values.put("MATKHAU", nd.getMatKhau());
        return db.insert("NGUOIDUNG", null, values);

    }
    @SuppressLint("Range")
    private List<NguoiDung> getData(String sql, String... selectionArgs){
        List<NguoiDung> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while(cursor.moveToNext()){
            NguoiDung obj = new NguoiDung();
            obj.setIdNguoiDung(cursor.getString(cursor.getColumnIndex("IDNGUOIDUNG")));
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("HOTEN")));
            obj.setTenTaiKhoan(cursor.getString(cursor.getColumnIndex("TENTAIKHOAN")));
            obj.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
            obj.setSoDienThoai(cursor.getString(cursor.getColumnIndex("SODIENTHOAI")));
            obj.setMatKhau(cursor.getString(cursor.getColumnIndex("MATKHAU")));
            list.add(obj);
        }
        return list;
    }
}
