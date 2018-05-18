package com.hongenit.picseei18n

import android.app.Application
import android.content.Context
import android.os.Handler
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure


/**
 * Created by hongenit on 18/2/1.
 * function:
 */
class PicSeeApplication : Application() {


    val TAG = "PicSeeApplication"

    override fun onCreate() {
        super.onCreate()
        sContext = applicationContext
        DataModel.getInstance().initDataModel(sContext)
        DataModel.getInstance().queryAllFavouritePics()
        sFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        initUmeng()
    }

    private fun initUmeng() {
        UMConfigure.init(this, "5afd8de9f29d9874fa0006de", Constants.UMENG_CHANNEL_NAME, UMConfigure.DEVICE_TYPE_PHONE, "7949ff424302b34a1fb5ffc0d2cec3b9")
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL)
        UMConfigure.setLogEnabled(true)
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