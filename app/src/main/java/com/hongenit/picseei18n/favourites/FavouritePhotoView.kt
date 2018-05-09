package com.hongenit.picseei18n.favourites

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.hongenit.picseei18n.R
import com.hongenit.picseei18n.util.ImageLoadUtil
import kotlinx.android.synthetic.main.item_favourite_photo.view.*

/**
 * Created by Xiaohong on 2018/5/9.
 * desc:
 */
class FavouritePhotoView(context: Context) : FrameLayout(context) {
    private var mContext: Context

    init {
        mContext = context
        initView()
    }

    private fun initView() {
        val root_view = View.inflate(context, R.layout.item_favourite_photo, rootView as ViewGroup?)
    }

    fun setPic(url: String) {
        ImageLoadUtil.newInstance()?.loadRoundImage(mContext, ivFavouritePic, url)
    }


}