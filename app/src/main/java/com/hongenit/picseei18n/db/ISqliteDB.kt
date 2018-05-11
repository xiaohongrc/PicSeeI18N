package com.hongenit.picseei18n.db

import com.hongenit.picseei18n.detail.PicBean

/**
 * Created by Xiaohong on 2018/5/10.
 * desc:
 */
interface ISqliteDB {
    fun insertFavouritePic(picBean: PicBean)


    fun queryAllFavouritePics(): ArrayList<PicBean>

}