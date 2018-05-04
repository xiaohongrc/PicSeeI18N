package com.hongenit.picseei18n.picClassify.commontab

import com.hongenit.picseei18n.net.ResponseListener
import com.hongenit.picseei18n.net.WebServiceImpl
import java.util.*

/**
 * Created by hongenit on 18/1/31.
 * 获取图片信息的model
 */


private val TAG: String = "CommonTabModel"


//http://www.win4000.com/wallpaper_192_0_0_1.html
const val WEBSITE_PREFIX_MSGAO = "http://www.msgao"
const val WEBSITE_PREFIX_WIN4000 = "http://www.win4000"

class CommonTabModel() {
    val webservice = WebServiceImpl()

    lateinit var mPressenter: CommonTabPresenter

    constructor(commonTabPresenter: CommonTabPresenter) : this() {
        mPressenter = commonTabPresenter
    }

    fun reqOutList(url: String, page: Int, response: ResponseListener) {
        val urlWithParam = url + "?page=" + page + "country=" + Locale.getDefault().country
        webservice.getAlbumInfoList(urlWithParam, response)
    }


}