package com.thealienobserver.nikhil.travon.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageViewPagerAdapter extends PagerAdapter {
    private String[] imageUrls;
    private Context context;

    public ImageViewPagerAdapter(Context context, String[] imageUrls){
        this.imageUrls = imageUrls;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageUrls.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView image = new ImageView(context);
        ViewGroup.LayoutParams imageLayout = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        image.setLayoutParams(imageLayout);
        Glide.with(context).load(imageUrls[position]).into(image);
        container.addView(image);
        return image;
//        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    //    @Override
//    public Object getItem(int position) {
//        return imageUrls[position];
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView image = new ImageView(context);
//        ViewGroup.LayoutParams imageLayout = new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//        );
//        image.setLayoutParams(imageLayout);
//        Glide.with(context).load(getItemId(position)).into(image);
//        return image;
//    }
}
