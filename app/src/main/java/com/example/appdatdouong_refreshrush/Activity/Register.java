package com.example.appdatdouong_refreshrush.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appdatdouong_refreshrush.Dao.NguoiDungDAO;
import com.example.appdatdouong_refreshrush.Model.NguoiDung;
import com.example.appdatdouong_refreshrush.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    NguoiDungDAO ndDAO;
    ArrayList<NguoiDung> list;
    Button BTN_signup_next;
    TextInputLayout TXTL_signup_HoVaTen, TXTL_signup_TenDN, TXTL_signup_Email, TXTL_signup_SDT, TXTL_signup_MatKhau, TXTL_signup_idNguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        BTN_signup_next = findViewById(R.id.btn_signup_next);
        TXTL_signup_HoVaTen = findViewById(R.id.txtl_signup_HoVaTen);
        TXTL_signup_TenDN = findViewById(R.id.txtl_signup_TenDN);
        TXTL_signup_Email = findViewById(R.id.txtl_signup_Email);
        TXTL_signup_SDT = findViewById(R.id.txtl_signup_SDT);
        TXTL_signup_MatKhau = findViewById(R.id.txtl_signup_MatKhau);
        TXTL_signup_idNguoiDung = findViewById(R.id.txtl_signup_idNguoiDung);
        ndDAO= new NguoiDungDAO(this);
        BTN_signup_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }
    private void register(){
        String id = TXTL_signup_idNguoiDung.getEditText().getText().toString().trim();
        String hoTen = TXTL_signup_HoVaTen.getEditText().getText().toString().trim();
        String tenDN = TXTL_signup_TenDN.getEditText().getText().toString().trim();
        String email = TXTL_signup_Email.getEditText().getText().toString().trim();
        String sdt = TXTL_signup_SDT.getEditText().getText().toString().trim();
        String matKhau = TXTL_signup_MatKhau.getEditText().getText().toString().trim();
        if (id.isEmpty() || hoTen.isEmpty() || tenDN.isEmpty() || email.isEmpty() || sdt.isEmpty() || matKhau.isEmpty()){
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }if (ndDAO.isMaNvExists(id)){
            Toast.makeText(this, "ID này đã tồn tại", Toast.LENGTH_SHORT).show();
            return;
        }if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Email kh đúng định dạng", Toast.LENGTH_SHORT).show();
            return;
        }if (!Patterns.PHONE.matcher(sdt).matches() || sdt.length() != 10){
            Toast.makeText(this, "Số điện thoại phải đủ 10 ký tự", Toast.LENGTH_SHORT).show();
            return;
        }
        NguoiDung newNguoiDung = new NguoiDung(id, hoTen, tenDN, email, sdt, matKhau);

        long result = ndDAO.insert(newNguoiDung);
        if (result > 0){
            Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Register.this, ManHinh_DangNhap.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
        }
    }
}