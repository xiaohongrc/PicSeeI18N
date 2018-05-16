package com.hongenit.picseei18n.picClassify.commontab

import android.os.Bundle
import com.hongenit.picseei18n.net.ResponseListener
import com.hongenit.picseei18n.net.WebServiceImpl
import com.hongenit.picseei18n.util.EventUtil
import java.util.*

/**
 * Created by hongenit on 18/1/31.
 * 获取图片信息的model
 */


private val TAG: String = "CommonTabModel"


class CommonTabModel() {
    val webservice = WebServiceImpl()

    lateinit var mPressenter: CommonTabPresenter

    constructor(commonTabPresenter: CommonTabPresenter) : this() {
        mPressenter = commonTabPresenter
    }

    fun reqOutList(url: String, page: Int, response: ResponseListener) {
        val urlWithParam = url + "&page=" + page + "&country=" + Locale.getDefault().country
        val bundle = Bundle()
        bundle.putString(EventUtil.FirebaseEventParams.urlWithParam, urlWithParam)
        EventUtil.request_classify()
        webservice.getAlbumInfoList(urlWithParam, response)
    }


}