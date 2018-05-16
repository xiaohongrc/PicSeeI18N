package com.hongenit.picseei18n

import android.app.Application
import android.content.Context
import android.os.Handler
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics


/**
 * Created by hongenit on 18/2/1.
 * function:
 */
class PicSeeApplication : Application() {


    val TAG = "PicSeeApplication"

    override fun onCreate() {
        sContext = applicationContext
        DataModel.getInstance().initDataModel(sContext)
        DataModel.getInstance().queryAllFavouritePics()
        sFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        super.onCreate()
    }


    companion object {
        lateinit var sContext: Context
        val mHandler: Handler = Handler()
        lateinit var sFirebaseAnalytics: FirebaseAnalytics

        fun getAppContext(): Context? {
            return sContext
        }

        fun runOnUiThread(runnable: Runnable) {
            mHandler.post(runnable)
        }

        fun getFirebaseEventLogger(): FirebaseAnalytics {
            return sFirebaseAnalytics
        }
    }


}