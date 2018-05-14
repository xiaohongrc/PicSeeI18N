package com.hongenit.picseei18n;

import android.os.Build;
import android.os.Environment;

import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * Created by hongenit on 2018/2/4.
 * constant values
 */

public interface Constants {

    File APP_DIR = new File(Environment.getExternalStorageDirectory(), ".PicSeeSee");

    // 缓存文档的目录
    File CACHE_DOCUMENTS = new File(APP_DIR, "cache_documents");
    File CACHE_IMAGES = new File(APP_DIR, "images");

    String UMENG_CHANNEL_NAME = "baidu";


    String ADMOB_APP_ID = "ca-app-pub-1616641096587052~3240065845";
    // admob ID of banner 1
    String ADMOB_banner_ID1 = "ca-app-pub-1616641096587052/7393690000";

    @NotNull
    String SDK_VERSION = String.valueOf(Build.VERSION.SDK_INT);
}
