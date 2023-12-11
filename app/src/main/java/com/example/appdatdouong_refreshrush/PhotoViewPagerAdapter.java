package com.example.appdatdouong_refreshrush;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class PhotoViewPagerAdapter extends PagerAdapter {
    List<Photo> listPhoto;

    public PhotoViewPagerAdapter(List<Photo> listPhoto) {
        this.listPhoto = listPhoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(container.getContext());
        View view = layoutInflater.inflate(R.layout.item_image, container, false);
        ImageView imageView = view.findViewById(R.id.img_photo);
        Photo photo = listPhoto.get(position);
        imageView.setImageResource(photo.getId());
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if (listPhoto!=null){
            return listPhoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
