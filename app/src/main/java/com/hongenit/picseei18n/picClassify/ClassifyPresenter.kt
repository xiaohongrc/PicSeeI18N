package com.hongenit.picseei18n.picClassify

class ClassifyPresenter(styleClassifyFragment: StyleClassifyFragment) {
    val mView: StyleClassifyFragment = styleClassifyFragment

    // 请求数据
    fun requestClassifyData() {
        ClassifyModel(this).getClassifyList()
    }

    // 请求到了数据的回调
    fun requestDataComplete(result: ArrayList<ClassifyTypeBean>) {
        println("requestDataComplete")

        mView.RefreshData(result)



    }


}
