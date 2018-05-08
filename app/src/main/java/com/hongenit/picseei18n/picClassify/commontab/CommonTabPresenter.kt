package com.hongenit.picseei18n.picClassify.commontab

import android.content.Context
import com.bumptech.glide.Glide.init
import com.hongenit.picseei18n.PicSeeApplication
import com.hongenit.picseei18n.R
import com.hongenit.picseei18n.net.ResponseListener
import com.hongenit.picseei18n.picClassify.AlbumBean
import com.hongenit.picseei18n.picClassify.ClassifyTypeBean
import com.hongenit.picseei18n.picClassify.ZResponse
import com.hongenit.picseei18n.util.LogUtil
import com.hongenit.picseei18n.util.ToastUtil

/**
 * Created by hongenit on 18/1/31.
 * 展示图片的presenter
 */
private val TAG: String = "CommonTabPresenter"

class CommonTabPresenter(context: Context) : ICommonTabPresenter, CommonTabResponsListener() {
    override fun onAnalyzeComplete(picList: ArrayList<AlbumBean>) {
        PicSeeApplication.runOnUiThread(Runnable {
            run {
                if (mView.isVisible) {
                    if (isLoadMore) {
                        mView.addData(picList)
                    } else {
                        mView.replaceData(picList)
                    }
                }
            }
        })

    }

    override fun onAnalyzeError() {
        LogUtil.e(TAG, "error")

    }

    var isLoadMore: Boolean = false
    lateinit var mContext: Context
    lateinit var mCommonTabModel: CommonTabModel

    init {
        mContext = context
        mCommonTabModel = CommonTabModel(this)
    }

//    override fun onSuccess(picList: ArrayList<AlbumBean>) {
//        if (mView.isVisible) {
//            if (isLoadMore) {
//                mView.addData(picList)
//            } else {
//                mView.replaceData(picList)
//            }
//        }
//
//    }
//    override fun onError(msg: String?) {
//        if (isLoadMore){
//            ToastUtil.showToast(mContext.getString(R.string.no_more_data))
//        }else{
//            ToastUtil.showToast(mContext.getString(R.string.msg_get_data_error))
//        }
//    }


    override fun start(url: String) {
        mCommonTabModel.reqOutList(url, 0, this)
    }

    private lateinit var mView: CommonTabFragment

    override fun setView(fragment: CommonTabFragment) {
        mView = fragment
    }

    private var mPageNum: Int = 0

    override fun requestData(isLoadMore: Boolean, url: String) {
        this.isLoadMore = isLoadMore
        if (isLoadMore) {
            mPageNum++
        } else {
            mPageNum = 0
        }
        mCommonTabModel.reqOutList(url, mPageNum, this)

    }


}
