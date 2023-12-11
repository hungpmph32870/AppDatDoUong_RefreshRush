package com.example.appdatdouong_refreshrush.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdatdouong_refreshrush.Adapter.DoUongAdapter;
import com.example.appdatdouong_refreshrush.Dao.DoUongDAO;
import com.example.appdatdouong_refreshrush.Model.DoUong;
import com.example.appdatdouong_refreshrush.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class QLDoUong extends Fragment {

    RecyclerView rcvDoUong;
    static DoUongDAO dao;
    DoUongAdapter adapter;
    private ArrayList<DoUong> list = new ArrayList<>();
    FloatingActionButton fltadd;
    EditText edsearch;
    ArrayList<DoUong> tempList ;
    private String userType; // Declare userType variable
    private String maKh;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_do_uong, container, false);
        rcvDoUong = view.findViewById(R.id.rcv_doUong);
        dao = new DoUongDAO(getActivity());
        fltadd = view.findViewById(R.id.fltadd_douong);
        edsearch = view.findViewById(R.id.ed_search);
        list = dao.selectAll();
        LinearLayoutManager layoutManager =  new LinearLayoutManager(getActivity());
        rcvDoUong.setLayoutManager(layoutManager);
        adapter = new DoUongAdapter(getActivity(), list, userType, maKh );
        rcvDoUong.setAdapter(adapter);
        maKh = requireContext().getSharedPreferences("user_prefs", requireContext().MODE_PRIVATE)
                .getString("maKh", "");
        Bundle args = getArguments();
        if (args != null) {
            userType = args.getString("USER_TYPE");
            list.addAll(dao.selectAll());
            adapter = new DoUongAdapter(getContext(), list, userType, maKh);
            rcvDoUong.setAdapter(adapter);
            // Check USER_TYPE and set visibility accordingly
            if ("CUSTOMER".equals(userType)) {
                fltadd.setVisibility(View.GONE);
            } else {
                fltadd.setVisibility(View.VISIBLE);
                fltadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        opendialogthem();
                    }
                });
            }
        } else {
            Log.d("Fragment_SanPham", "Arguments are null");
        }


        edsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                list.clear();
                for (DoUong obj : tempList){
                    if (obj.getTenDoUong().contains(charSequence.toString())){
                        list.add(obj);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialogthem();
            }
        });
        capNhat();
        return view;
    }
    public void capNhat(){
        tempList = (ArrayList<DoUong>)dao.selectAll();
        LinearLayoutManager layoutManager =  new LinearLayoutManager(getActivity());
        rcvDoUong.setLayoutManager(layoutManager);
        adapter = new DoUongAdapter(getActivity(), list, userType, maKh);
        rcvDoUong.setAdapter(adapter);
    }
    public  void  opendialogthem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_douong, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();


        EditText edtten = view.findViewById(R.id.ed_tendouong);
        EditText edtgia = view.findViewById(R.id.ed_giadouong);
        EditText edttinhtrang = view.findViewById(R.id.ed_trangThai);
        EditText edtdiachi = view.findViewById(R.id.ed_diachi);
        Button btnadd = view.findViewById(R.id.btnSave_DU);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edtten.getText().toString();
                int gia = Integer.parseInt(edtgia.getText().toString());
                String trangthai = edttinhtrang.getText().toString();
                String diachi = edtdiachi.getText().toString();
                DoUong obj = new DoUong(ten, gia, trangthai, diachi);
                if (dao.insert(obj)) {
                    list.clear();
                    list.addAll(dao.selectAll());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


