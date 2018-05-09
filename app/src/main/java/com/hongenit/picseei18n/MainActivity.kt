package com.hongenit.picseei18n

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.view.View
import com.google.android.gms.ads.MobileAds
import com.hongenit.picseei18n.favourites.FavouritesActivity
import com.hongenit.picseei18n.picClassify.StyleClassifyFragment
import com.hongenit.picseei18n.util.LogUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v) {
            tv_menu_item1 -> {
                val intent = Intent(this@MainActivity, FavouritesActivity::class.java)
                startActivity(intent)
            }
            close_menu -> {
                if (drawer_layout.isDrawerOpen(rl_menu))
                    drawer_layout.closeDrawer(rl_menu)
            }

        }

    }

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initAdMob()
    }

    private fun initAdMob() {
        MobileAds.initialize(this, Constants.ADMOB_APP_ID)
    }

    private fun initView() {
        val supportFragmentManager = supportFragmentManager
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.fl_main_content, StyleClassifyFragment.newInstance())
        beginTransaction.commit()

        initDrawer()

    }

    private fun initDrawer() {
        var versionName = "1.0"
        try {
            versionName = packageManager.getPackageInfo(packageName, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        app_version.setText(versionName)
        drawer_layout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(newState: Int) {
                println("new state  =" + newState)
            }

            override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {
                //设置主布局随菜单滑动而滑动
                val drawerViewWidth = drawerView?.width
                fl_main_content.setTranslationX(drawerViewWidth!! * slideOffset)

                //设置控件最先出现的位置
                val padingLeft = drawerViewWidth.toDouble() * (1 - 0.618) * (1 - slideOffset).toDouble()
                rl_menu.setPadding(padingLeft.toInt(), 0, 0, 0)


            }

            override fun onDrawerClosed(drawerView: View?) {
                LogUtil.i(TAG, "onDrawerClosed")
            }

            override fun onDrawerOpened(drawerView: View?) {
                LogUtil.i(TAG, "onDrawerOpened")
            }
        })


        tv_menu_item1.setOnClickListener(this)
        close_menu.setOnClickListener(this)


    }


}
