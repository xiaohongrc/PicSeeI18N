package com.hongenit.picseei18n

import android.os.Bundle
import com.hongenit.picseei18n.picClassify.StyleClassifyFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView() {
        val supportFragmentManager = supportFragmentManager
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.fl_main_content, StyleClassifyFragment.newInstance())
        beginTransaction.commit()
    }





}
