package com.hongenit.picseei18n.detail

import com.hongenit.picseei18n.R
import com.hongenit.picseei18n.PicSeeApplication
import com.hongenit.picseei18n.util.LogUtil
import com.hongenit.picseei18n.util.ToastUtil

/**
 * Created by hongenit on 18/2/2.
 * 图片详情的presenter
 */
class DetailPresenter : IDetailPresenter, DetailResponse {
    override fun onSuccess(picList: ArrayList<PicBean>) {
        if (!mView.isFinishing) {
//            mView.replaceData(picList)
        }
        mView.showLoadingView(false)
    }

    val TAG: String = "DetailPresenter"



    override fun onError(msg: String?) {
        LogUtil.e(TAG, "msg = $msg")
        ToastUtil.showToast(PicSeeApplication.getAppContext()!!.getString(R.string.load_detail_error))
        mView.showLoadingView(false)
    }


    override fun showBigImg() {

    }

    private lateinit var mView: DetailsActivity

    override fun start(view: DetailsActivity) {
        mView = view
    }

//    override fun requestData(url: String) {
//        mView.showLoadingView(true)
////        DetailModel.requestDetails(url, 1, this)
//    }
}