package com.hongenit.picseei18n.net

import okhttp3.Request

/**
 * Created by hongenit on 18/1/29.
 * 网络请求的实现类
 */


interface INetClient {

    fun sendRequest(request: Request, listener: ResponseListener)


    fun sendRequest(request: Request): String
}