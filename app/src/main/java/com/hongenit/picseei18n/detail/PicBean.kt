package com.hongenit.picseei18n.detail

import java.io.Serializable

/**
 * Created by hongenit on 18/1/31.
 * 图片数据类
 */
data class PicBean(var url: String, var favouriteTime: Long) : Serializable {
    override fun toString(): String {
        return "PicBean(url='$url', favouriteTime=$favouriteTime)"
    }

    override fun equals(other: Any?): Boolean {
        if (other is PicBean) {
            val otherPic: PicBean = other as PicBean
            if (otherPic.url == this.url) {
                return true
            }
        }
        return super.equals(other)
    }

}