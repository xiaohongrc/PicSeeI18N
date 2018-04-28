package com.hongenit.picseei18n.picClassify

import com.hongenit.picseei18n.PicSeeApplication
import com.hongenit.picseei18n.net.RequestUrl
import com.hongenit.picseei18n.net.WebServiceImpl
import com.hongenit.picseei18n.util.LogUtil
import com.hongenit.picseei18n.util.ToastUtil

/**
 * Created by Xiaohong on 2018/4/26.
 * desc: 分类的数据类
 */
class ClassifyModel() {
    lateinit var mPressenter: ClassifyPresenter

    constructor(classifyPresenter: ClassifyPresenter) : this() {
        mPressenter = classifyPresenter
    }

    val webservice = WebServiceImpl()

//    companion object {
//        lateinit var mInstances: ClassifyModel
//        fun getInstance(): ClassifyModel {
//            if (mInstances == null) {
//                mInstances = ClassifyModel()
//            }
//            return mInstances
//        }
//    }

    fun getClassifyList() {
        val url = RequestUrl.CLASSIFY_LIST
        webservice.getClassifyList(url, object : ClassifyResponseListener() {
            override fun onAnalyzeComplete(result: ArrayList<ClassifyTypeBean>) {

                PicSeeApplication.runOnUiThread(Runnable {
                    run {
                        mPressenter.requestDataComplete(result)
                    }

                })

            }

            override fun onAnalyzeError() {
                LogUtil.e("ClassifyModel","get classify data error")
            }

        })
    }


}