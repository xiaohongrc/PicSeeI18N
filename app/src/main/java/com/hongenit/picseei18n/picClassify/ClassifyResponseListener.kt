package com.hongenit.picseei18n.picClassify

import com.hongenit.picseei18n.net.ResponseListener
import okhttp3.Response
import org.json.JSONArray
import java.util.concurrent.locks.ReentrantLock

/**
 * Created by Xiaohong on 2018/4/27.
 * desc:
 */
open abstract class ClassifyResponseListener : ResponseListener() {
    override fun onComplete(p1: Response?) {

        onAnalyzeComplete(AnalyzeResposeData(p1))
    }

    private fun AnalyzeResposeData(p1: Response?): ArrayList<ClassifyTypeBean> {
        val jsonResponse = p1?.body()?.string()
        println("ClassifyResponseListener = " + p1?.headers() + " body = " + jsonResponse)
//        {
//            "_id": {
//            "$oid": "5adde79bec82f808b7b85c7a"
//        },
//            "classifyUrl": "http://www.win4000.com/mobile_2338_0_0_1.html",
//            "classifyTitle": "明星"
//        }
        val resultList = ArrayList<ClassifyTypeBean>()

        val jsonArray = JSONArray(jsonResponse)
        val length = jsonArray.length()
        for (i in 0..length-1) {
            val classifyJson = jsonArray.getJSONObject(i)
            val classifyUrl = classifyJson.getString("classifyUrl")
            val classifyTitle = classifyJson.getString("classifyTitle")
            resultList.add(ClassifyTypeBean(classifyUrl, classifyTitle))
        }

        return resultList

    }

    override fun onError() {
        println("ClassifyResponseListener error ")
        onAnalyzeError()

    }

    abstract fun onAnalyzeComplete(result: ArrayList<ClassifyTypeBean>)
    abstract fun onAnalyzeError()


}