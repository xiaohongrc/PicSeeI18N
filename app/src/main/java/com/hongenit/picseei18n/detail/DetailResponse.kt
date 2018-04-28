package com.hongenit.picseei18n.detail

/**
 * Created by hongenit on 18/1/31.
 */
interface DetailResponse {

    fun onSuccess(picList: ArrayList<PicBean>)

    fun onError(msg: String?)
}