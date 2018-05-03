package com.hongenit.picseei18n.net

import okhttp3.*
import com.hongenit.picseei18n.util.LogUtil
import java.io.IOException

/**
 * Created by hongenit on 18/1/29.
 * 网络请求实现类，基于okhttp3实现
 */

class NetClient : INetClient {

    private val TAG = javaClass.simpleName
    override fun sendRequest(request: Request, listener: ResponseListener) {
//        val request = Request.Builder().url(url).build()
        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(p0: Call?, p1: IOException?) {
                LogUtil.e(TAG, p1.toString())
                listener.onError()
            }

            override fun onResponse(p0: Call?, p1: Response?) {

                LogUtil.i(TAG, p1.toString())
                listener.onComplete(p1)
            }
        })
    }


    override fun sendRequest(request: Request): String {
//        val request = Request.Builder().url(url).build()
        var response = ""
        response = OkHttpClient().newCall(request).execute().toString()

        return response

    }


}
