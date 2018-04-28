package com.hongenit.picseei18n.net

import okhttp3.Request

/**
 * Created by Xiaohong on 2018/4/26.
 * desc:
 */
class WebServiceImpl:IWebService {
    private val INetClient = NetClient()
    override fun getClassifyList(url :String ,listener:ResponseListener){
        val request = Request.Builder().url(url).build()
        INetClient.sendRequest(request,listener)

    }

    override fun getAlbumInfoList() {

    }


}