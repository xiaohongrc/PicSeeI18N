package com.hongenit.picseei18n.picClassify

import com.hongenit.picseei18n.net.ResponseListener
import com.hongenit.picseei18n.util.LogUtil
import okhttp3.Response
import org.json.JSONArray
import java.util.concurrent.locks.ReentrantLock

/**
 * Created by Xiaohong on 2018/4/27.
 * desc:
 */
open abstract class ClassifyResponseListener : ResponseListener() {
    val TAG = "ClassifyResponseListener"
    override fun onComplete(p1: Response?) {

        onAnalyzeComplete(AnalyzeResposeData(p1))
    }

    private fun AnalyzeResposeData(p1: Response?): ArrayList<ClassifyTypeBean> {
        val jsonResponse = p1?.body()?.string()
        val resultList = ArrayList<ClassifyTypeBean>()
        LogUtil.d(TAG, jsonResponse!!)

        val jsonArray = JSONArray(jsonResponse)
        val length = jsonArray.length()
        for (i in 0..length - 1) {
            val classifyJson = jsonArray.getJSONObject(i)
            val classifyUrl = classifyJson.getString("classifyUrl")
            val classifyTitle = classifyJson.getString("classifyTitle")
            resultList.add(ClassifyTypeBean(classifyUrl, classifyTitle))
        }

        return resultList

    }

    override fun onError() {
        LogUtil.e(TAG, "ClassifyResponseListener error ")
        onAnalyzeError()

    }

    abstract fun onAnalyzeComplete(result: ArrayList<ClassifyTypeBean>)
    abstract fun onAnalyzeError()


}