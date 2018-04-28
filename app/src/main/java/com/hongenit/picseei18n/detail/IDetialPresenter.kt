package com.hongenit.picseei18n.detail

/**
 * Created by hongenit on 18/2/2.
 * desc:
 */
interface IDetailPresenter {
    fun showBigImg()

    fun start(view: DetailsActivity)

    fun requestData(url: String)


}