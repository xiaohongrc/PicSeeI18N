package com.hongenit.picseei18n.favourites

import android.app.Activity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.hongenit.picseei18n.BaseActivity
import com.hongenit.picseei18n.R
import com.hongenit.picseei18n.picClassify.StyleClassifyFragment

/**
 * Created by Xiaohong on 2018/5/9.
 * desc:
 */
class FavouritesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*set it to be no title*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*set it to be full screen*/
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_favourite)
//        initFragment()
    }

    private fun initFragment() {
        val supportFragmentManager = supportFragmentManager
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.fl_main_content, StyleClassifyFragment.newInstance())
        beginTransaction.commit()
    }


}