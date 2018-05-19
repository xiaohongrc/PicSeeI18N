package com.hongenit.picseei18n.setting

import android.os.Bundle
import android.view.View
import com.hongenit.picseei18n.BaseActivity
import com.hongenit.picseei18n.Constants
import com.hongenit.picseei18n.R
import com.hongenit.picseei18n.util.FileUtil
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * Created by Xiaohong on 2018/5/17.
 * desc:
 */
class SettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        tv_download_path.setText(Constants.CACHE_IMAGES.absolutePath)

    }


}