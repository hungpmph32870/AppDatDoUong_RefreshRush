package com.example.appdatdouong_refreshrush.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "QLSP";

    public DbHelper(Context context){super(context, DB_NAME, null, 34);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table NhanVien (" +
                "maNv Text primary key ," +
                "tenNv TEXT not null," +
                "matKhauNv TEXT not null," +
                "soNv integer not null," +
                "emailNv TEXT not null," +
                "anhNv text not null)");
        sqLiteDatabase.execSQL("insert into NhanVien values " +
                "('Admin','Vũ Đình Khoa ','123456',0899501203,'khoavdph17970@fpt.edu.vn','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUYjtgg39whpZmugBXYoTbp296AqxVA3yzuw&usqp=CAU')," +
                "('Admin2','Phùng Mạnh Hùng','123456',0899501203,'khoavdph17970@fpt.edu.vn','https://afamilycdn.com/150157425591193600/2022/8/23/299980243-2045230712333844-1983650452575958652-n-3730-1661222602446-16612226025621952261667.jpeg')," +
                "('Admin3','Trần Hồng Quân ','123456',0899501203,'khoavdph17970@fpt.edu.vn','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWjoyF0mcOPxktb_6WpDO84CsHyXT0a_0HQg&usqp=CAU')"
                );
        sqLiteDatabase.execSQL("create table KhachHang (" +
                "maKh text primary key," +
                "tenKh TEXT not null," +
                "matKhauKh TEXT not null," +
                "soKh integer not null," +
                "emailKh TEXT not null," +
                "diaChiKh TEXT not null," +
                "anhKh text not null)");
        sqLiteDatabase.execSQL("insert into KhachHang values " +
                "('KH01','Nguyễn Tuấn ','123',0366691234,'dungntph@fpt.edu.vn','Hn','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUYjtgg39whpZmugBXYoTbp296AqxVA3yzuw&usqp=CAU')," +
                "('KH02','Cao Hoàng','123',0366691234,'dungntph@fpt.edu.vn','Hn','https://afamilycdn.com/150157425591193600/2022/8/23/299980243-2045230712333844-1983650452575958652-n-3730-1661222602446-16612226025621952261667.jpeg')," +
                "('KH03','Tuấn Anh ','123',0366691234,'dungntph@fpt.edu.vn','Hn','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWjoyF0mcOPxktb_6WpDO84CsHyXT0a_0HQg&usqp=CAU')," +
                "('KH04','Nguyễn Tuấn ','123',0366691234,'dungntph@fpt.edu.vn','Hn','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCTllYr56xg6PDvJCwRevKDgz1xBxYVAX_Zw&usqp=CAU')," +
                "('KH05','Nguyễn Tuấn ','123',0366691234,'dungntph@fpt.edu.vn','Hn','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTSDw7Aw1I-FC4KkJzqHiON1gSeKVb54XzwRw&usqp=CAU')" +
                "");
        sqLiteDatabase.execSQL("CREATE TABLE DOUONG (" +
                "IDDOUONG INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "TENDOUONG TEXT NOT NULL,"+
                "GIADOUONG INTEGER NOT NULL,"+
                "SOLUONG INTEGER NOT NULL,"+
                "TINHTRANG TEXT NOT NULL,"+
                "DIACHI TEXT NOT NULL)");
        sqLiteDatabase.execSQL("INSERT INTO DOUONG VALUES(1, 'STING', 123000,1, 'sale', 'Cầu Giấy - Hà Nội')," +
                " (2, 'BIA 333', 12000,1, 'Khong Sale', 'Cầu Giấy - Hà Nội')," +
                " (3, 'Nuoc Dua', 10000,1, 'sale', 'Cầu Giấy - Hà Nội')," +
                " (4, 'Nuoc Tao', 10000,1, 'sale', 'Cầu Giấy - Hà Nội')");
        sqLiteDatabase.execSQL("create table GioHang (" +
                "maGio integer primary key autoincrement," +
                "soLuong integer not null," +
                "diaChiGio text not null," +
                "IDDOUONG integer references DOUONG(IDDOUONG), " +
                "maKh text references KhachHang(maKh) )");

        sqLiteDatabase.execSQL("create table DonHang (" +
                "maDon integer primary key autoincrement," +
                "ngayLap date not null," +
                "trangThaiDon text not null," +
                "maGio integer references GioHang(maGio)," +
                "maKh text references KhachHang(maKh) )");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i!=i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS KhachHang");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NhanVien");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DOUONG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS GioHang");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DonHang");
            onCreate(sqLiteDatabase);
        }
    }
}
