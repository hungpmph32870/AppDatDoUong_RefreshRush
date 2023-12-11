package com.example.appdatdouong_refreshrush.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.appdatdouong_refreshrush.Adapter.DoUongAdapter;
import com.example.appdatdouong_refreshrush.Dao.DoUongDAO;
import com.example.appdatdouong_refreshrush.Model.DoUong;
import com.example.appdatdouong_refreshrush.Photo;
import com.example.appdatdouong_refreshrush.PhotoViewPagerAdapter;
import com.example.appdatdouong_refreshrush.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class TrangChu extends Fragment {


    private RecyclerView rcvdouongsale;
    private DoUongAdapter doUongAdapter;
    private DoUongDAO doUongDAO;
    private String userType; // Declare userType variable
    private String maKh;
    ImageView btnxemthem;

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private List<Photo> listPhoto;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager.getCurrentItem() == listPhoto.size() - 1) {
                viewPager.setCurrentItem(0);
            } else {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        viewPager = view.findViewById(R.id.view_page);
        btnxemthem = view.findViewById(R.id.btn_trangchu_xemthem);
        circleIndicator = view.findViewById(R.id.circle_indicator);
        listPhoto = getListPhoto();
        PhotoViewPagerAdapter photoViewPagerAdapter = new PhotoViewPagerAdapter(listPhoto);
        viewPager.setAdapter(photoViewPagerAdapter);
        circleIndicator.setViewPager(viewPager);

        //recyclerview do uong sale
        rcvdouongsale = view.findViewById(R.id.rcvdouongsale);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvdouongsale.setLayoutManager(layoutManager);
        doUongDAO = new DoUongDAO(getContext());
        List<DoUong> saleDoUongList = doUongDAO.getSaleSanPhamList();
        ArrayList<DoUong> arrayListSale = new ArrayList<>(saleDoUongList);
        Bundle args = getArguments();
        userType = args.getString("USER_TYPE");
        if (getContext() !=null){
            doUongAdapter = new DoUongAdapter(getContext(), arrayListSale, userType, maKh);
            rcvdouongsale.setAdapter(doUongAdapter);
        }

        handler.postDelayed(runnable, 2000);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 1000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        btnxemthem.setOnClickListener(this::onclick);
        return view;
    }




    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.bannera));
        list.add(new Photo(R.drawable.bannerb));
        list.add(new Photo(R.drawable.bannerc));
        return list;
    }

    @Override
    public void onPause() {
        super.onPause();
        viewPager.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewPager.postDelayed(runnable, 1000);
    }
    public void onclick(View v){
        int id= v.getId();
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav);
        switch (id){
            case R.id.btn_trangchu_xemthem:
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frmNav, new QLDoUong());
                transaction.addToBackStack(null);
                transaction.commit();
                navigationView.setCheckedItem(R.id.douong);
        }
    }
}