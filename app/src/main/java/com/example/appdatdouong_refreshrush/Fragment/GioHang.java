package com.example.appdatdouong_refreshrush.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.appdatdouong_refreshrush.Adapter.GioHangAdapter;
import com.example.appdatdouong_refreshrush.Dao.GioHangDao;
import com.example.appdatdouong_refreshrush.R;

import java.util.ArrayList;


public class GioHang extends Fragment {
    private ListView lvGioHang;
    private GioHangAdapter gioHangAdapter;
    private GioHangDao gioHangDao;
    private ArrayList<com.example.appdatdouong_refreshrush.Model.GioHang> gioHangList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gio_hang, container, false);
        lvGioHang = view.findViewById(R.id.lv_giohang);

        // Initialize GioHangDao
        gioHangDao = new GioHangDao(requireContext());

        // Update the list view based on the provided arguments
        capNhatDonHangListView();
        return view;
    }
    private void capNhatDonHangListView() {
        // Get arguments passed to the fragment
        Bundle args = getArguments();

        if (args != null && args.containsKey("MA_KH")) {
            // Get maKh from the arguments
            String maKh = args.getString("MA_KH");

            // Get the list of GioHang items for the specified maKh
            gioHangList = new ArrayList<>(gioHangDao.getGioHangsByMaKh(maKh));
        } else {
            // Get the list of all GioHang items
            gioHangList = new ArrayList<>(gioHangDao.getAllGioHangs());
        }

        // Check if the fragment is attached to an activity
        if (isAdded()) {
            // Initialize the adapter and set it to the list view
            gioHangAdapter = new GioHangAdapter(requireContext(), gioHangList, "", "");
            lvGioHang.setAdapter(gioHangAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Close the database connection
        gioHangDao.close();
    }
}