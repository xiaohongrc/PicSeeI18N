package com.hongenit.picseei18n.picClassify.commontab

import android.os.Bundle
import com.hongenit.picseei18n.Constants
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
        val urlWithParam = url + "&page=" + page + "&country=" + Constants.LOCALE_COUNTRY
        val hashMap: HashMap<String, String?>? = hashMapOf()
        if (Constants.LOCALE_COUNTRY != null) {
            hashMap?.put(Constants.LOCALE_COUNTRY, urlWithParam)
        }
        EventUtil.request_classify(hashMap)
        webservice.getAlbumInfoList(urlWithParam, response)
    }


}