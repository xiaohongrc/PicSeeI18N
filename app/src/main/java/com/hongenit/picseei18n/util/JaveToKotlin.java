package com.hongenit.picseei18n.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.hongenit.picseei18n.Constants;

import java.io.File;

/**
 * Created by hongenit on 2018/5/5.
 * java 转kotlin测试
 */

public class JaveToKotlin {

    void bitmap(){
        Drawable drawable = Drawable.createFromPath("");
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();

        DrawerLayout drawerLayout = null;
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });




    }

    public static File getImagefile(String filename){

        File file = new File(Constants.CACHE_IMAGES, filename);
        return file;
    }

}
