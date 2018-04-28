package com.hongenit.picseei18n

import android.app.Application
import android.content.Context
import android.os.Handler


/**
 * Created by hongenit on 18/2/1.
 * function:
 */
class PicSeeApplication : Application() {


    val TAG = "PicSeeApplication"
    override fun onCreate() {
        sContext = applicationContext


        super.onCreate()

    }


    companion object {
        lateinit var sContext: Context
        val mHandler: Handler = Handler()

        fun getAppContext(): Context? {
            return sContext
        }

        fun runOnUiThread(runnable: Runnable) {
            mHandler.post(runnable)
        }
    }


}