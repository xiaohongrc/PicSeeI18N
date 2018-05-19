package com.hongenit.picseei18n.net

import okhttp3.Response

/**
 * Created by Xiaohong on 2018/4/26.
 * desc:
 */
abstract class ResponseListener {

    abstract fun onComplete(jsonString: String?)

    abstract fun onError()

}