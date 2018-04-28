package com.hongenit.picseei18n.net

/**
 * Created by hongenit on 2018/4/26.
 * desc:
 */
interface IWebService {

    //    获取分类列表
    fun getClassifyList(url: String, listener: ResponseListener)


    //    获取分类下的图集列表
    fun getAlbumInfoList()


}