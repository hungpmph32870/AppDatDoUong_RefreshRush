package com.example.appdatdouong_refreshrush;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdatdouong_refreshrush.Activity.ManHinh_DangNhap;
import com.example.appdatdouong_refreshrush.Fragment.DoiMatKhau;
import com.example.appdatdouong_refreshrush.Fragment.Fragment_NhanVien;
import com.example.appdatdouong_refreshrush.Fragment.GioHang;
import com.example.appdatdouong_refreshrush.Fragment.QLDoUong;
import com.example.appdatdouong_refreshrush.Fragment.QuanLyDonHang;
import com.example.appdatdouong_refreshrush.Fragment.ThongTinNguoiDung;
import com.example.appdatdouong_refreshrush.Fragment.TrangChu;
import com.example.appdatdouong_refreshrush.Fragment.ThongKe;
import com.google.android.material.navigation.NavigationView;

public class Menu_navigation extends AppCompatActivity{
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nav;
    TextView tvUser;
    View headerView;
    private String userType;
    private String maKh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_navigation);
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav);
        setSupportActionBar(toolbar);
        headerView = nav.getHeaderView(0);
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        Intent intent = getIntent();
        userType = intent.getStringExtra("USER_TYPE");
        maKh = intent.getStringExtra("MA_KH");


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.trangchu){
                    TrangChu tc = new TrangChu();
                    replaceFrg(tc);
                } else if (item.getItemId()==R.id.thongke) {
                    ThongKe tk = new ThongKe();
                    replaceFrg(tk);
                } else if (item.getItemId()==R.id.doimatkhau) {
                    DoiMatKhau dmk = new DoiMatKhau();
                    replaceFrg(dmk);
                }else if (item.getItemId()==R.id.giohang){
                    GioHang gh = new GioHang();
                    replaceFrg(gh);
                }else if(item.getItemId()==R.id.quanlynhanvien){
                    if (userType != null && !"CUSTOMER".equals(userType)) {
                        setTitle("Quản lý nhân viên");
                        Fragment_NhanVien hsp = new Fragment_NhanVien();
                        replaceFrg(hsp);
                    }

                }

                else if (item.getItemId()==R.id.quanlydonhang) {
                    QuanLyDonHang qldh = new QuanLyDonHang();
                    replaceFrg(qldh);
                } else if (item.getItemId()==R.id.douong) {
                    QLDoUong qldu = new QLDoUong();
                    replaceFrg(qldu);
                } else if (item.getItemId()==R.id.logout) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Menu_navigation.this);
                    builder.setTitle("Đăng Xuất");
                    builder.setMessage("Bạn có muốn đăng xuất không ?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Menu_navigation.this, ManHinh_DangNhap.class);
                            Toast.makeText(Menu_navigation.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            Toast.makeText(Menu_navigation.this, "Không đăng xuất", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                return true;
            }
        });
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    public void replaceFrg(Fragment frg){
        Bundle bundle = new Bundle();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmNav, frg).commit();
        bundle.putString("USER_TYPE", userType);
        bundle.putString("MA_KH", maKh);
        frg.setArguments(bundle);

    }

}