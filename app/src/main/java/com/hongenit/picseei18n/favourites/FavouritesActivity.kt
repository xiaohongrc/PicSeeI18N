package com.hongenit.picseei18n.favourites

import android.app.Activity
import android.os.Bundle
import com.hongenit.picseei18n.BaseActivity
import com.hongenit.picseei18n.R
import com.hongenit.picseei18n.picClassify.StyleClassifyFragment

/**
 * Created by Xiaohong on 2018/5/9.
 * desc:
 */
class FavouritesActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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