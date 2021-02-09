package com.yifan.yffinancialinfo.ui.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.yifan.yffinancialinfo.R;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-02-09
 */
public class ImageViewAdapter {
    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }


    @BindingAdapter("imageUrl")
    public static void setSrc(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }


//    @BindingAdapter({"app:imageUrl", "app:placeHolder", "app:error"})
//    public static void loadImage(ImageView imageView, String url, Drawable holderDrawable, Drawable errorDrawable) {
//        Glide.with(imageView.getContext())
//                .load(url)
//                .placeholder(holderDrawable)
//                .error(errorDrawable)
//                .into(imageView);
//    }
}
