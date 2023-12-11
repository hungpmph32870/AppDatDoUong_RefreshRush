package com.example.appdatdouong_refreshrush.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appdatdouong_refreshrush.DataBase.DbHelper;
import com.example.appdatdouong_refreshrush.Model.DoUong;

import java.util.ArrayList;
import java.util.List;

public class DoUongDAO {
    private final DbHelper dbHelper;

    public DoUongDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public boolean delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("DOUONG", "IDDOUONG =?", new String[]{String.valueOf(id)});
        return row > 0;
    }

    public boolean insert(DoUong obj) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = getContentValues(obj);
        long row = db.insert("DOUONG", null, values);
        return row > 0;
    }

    public boolean update(DoUong obj) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = getContentValues(obj);
        long row = db.update("DOUONG", values, "IDDOUONG = ?", new String[]{String.valueOf(obj.getIdDoUong())});
        return row > 0;
    }

    public ArrayList<DoUong> selectAll() {
        ArrayList<DoUong> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM DOUONG", null);
            if (cursor.moveToFirst()) {
                do {
                    DoUong obj = getDoUongFromCursor(cursor);
                    list.add(obj);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<DoUong> getSaleSanPhamList() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<DoUong> saleList = new ArrayList<>();

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM DOUONG WHERE TINHTRANG = 'sale'", null);
            if (cursor.moveToFirst()) {
                do {
                    DoUong obj = getDoUongFromCursor(cursor);
                    saleList.add(obj);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return saleList;
    }

    private ContentValues getContentValues(DoUong obj) {
        ContentValues values = new ContentValues();
        values.put("TENDOUONG", obj.getTenDoUong());
        values.put("GIADOUONG", obj.getGiaDoUong());
        values.put("SOLUONG", obj.getSoLuong());
        values.put("TINHTRANG", obj.getTinhTrang());
        values.put("DIACHI", obj.getDiaChiQuan());
        return values;
    }
    @SuppressLint("Range")
    private DoUong getDoUongFromCursor(Cursor cursor) {
        DoUong obj = new DoUong();
        obj.setIdDoUong(cursor.getInt(cursor.getColumnIndex("IDDOUONG")));
        obj.setTenDoUong(cursor.getString(cursor.getColumnIndex("TENDOUONG")));
        obj.setGiaDoUong(cursor.getInt(cursor.getColumnIndex("GIADOUONG")));
        obj.setSoLuong(cursor.getInt(cursor.getColumnIndex("SOLUONG")));
        obj.setTinhTrang(cursor.getString(cursor.getColumnIndex("TINHTRANG")));
        obj.setDiaChiQuan(cursor.getString(cursor.getColumnIndex("DIACHI")));
        return obj;
    }
    @SuppressLint("Range")
    public DoUong getSanPhamByMaSp(String maSp) {
        DoUong doUong = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DOUONG WHERE IDDOUONG=?", new String[]{maSp});

        if (cursor != null && cursor.moveToFirst()) {
            doUong = new DoUong();
            doUong.setIdDoUong(cursor.getInt(cursor.getColumnIndex("IDDOUONG")));
            doUong.setTenDoUong(cursor.getString(cursor.getColumnIndex("TENDOUONG")));
            doUong.setGiaDoUong(cursor.getInt(cursor.getColumnIndex("GIADOUONG")));
            doUong.setSoLuong(cursor.getInt(cursor.getColumnIndex("SOLUONG")));
            doUong.setTinhTrang(cursor.getString(cursor.getColumnIndex("TINHTRANG")));
            doUong.setDiaChiQuan(cursor.getString(cursor.getColumnIndex("DIACHI")));
            cursor.close();
        }

        return doUong;
    }
}
