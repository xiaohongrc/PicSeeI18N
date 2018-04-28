package com.hongenit.picseei18n.picClassify.commontab

import com.hongenit.picseei18n.net.ResponseListener
import com.hongenit.picseei18n.picClassify.AlbumBean
import com.hongenit.picseei18n.picClassify.ClassifyTypeBean
import com.hongenit.picseei18n.util.LogUtil
import okhttp3.Response
import org.json.JSONArray

/**
 * Created by Xiaohong on 2018/4/28.
 * desc:
 */
abstract class CommonTabResponsListener : ResponseListener() {
    override fun onComplete(p1: Response?) {
        onAnalyzeComplete(AnalyzeResposeData(p1))
    }

//
//    "albumUrl": "http://www.win4000.com/mobile_detail_144855.html",
//    "_id": {
//        "$oid": "5ace1f60ec82f81323d5553f"
//    },
//    "albumPhotos": [{
//        "photoUrl": "http://pic1.win4000.com/mobile/2018-04-04/5ac4789ba2f72.jpg"
//    }, {
//        "photoUrl": "http://pic1.win4000.com/mobile/2018-04-04/5ac4789c99128.jpg"
//    }, {
//        "photoUrl": "http://pic1.win4000.com/mobile/2018-04-04/5ac4789d94f7a.jpg"
//    }, {
//        "photoUrl": "http://pic1.win4000.com/mobile/2018-04-04/5ac4789ea9459.jpg"
//    }, {
//        "photoUrl": "http://pic1.win4000.com/mobile/2018-04-04/5ac4789fb30ce.jpg"
//    }, {
//        "photoUrl": "http://pic1.win4000.com/mobile/2018-04-04/5ac478a0c2473.jpg"
//    }, {
//        "photoUrl": "http://pic1.win4000.com/mobile/2018-04-04/5ac478a1df72a.jpg"
//    }, {
//        "photoUrl": "http://pic1.win4000.com/mobile/2018-04-04/5ac478a31d397.jpg"
//    }, {
//        "photoUrl": "http://pic1.win4000.com/mobile/2018-04-04/5ac478a441168.jpg"
//    }],
//    "albumIntro": "蔡徐坤帅气图片高清手机壁纸。分享一组壁纸尺寸：690*1227。蔡徐坤（August），1998年8月2日出生于湖南省，中国内地男歌手、演员。更多蔡徐坤壁纸图片尽在美桌网。",
//    "width": "640",
//    "title": "蔡徐坤帅气图片高清手机壁纸",
//    "height": "1136",
//    "whichClassify": "明星",
//    "thumbnailUrl ": "http://pic1.win4000.com/mobile/2018-04-04/5ac478a441168_250_350.jpg"

    private fun AnalyzeResposeData(p1: Response?): ArrayList<AlbumBean> {
        val jsonResponse = p1?.body()?.string()
        println("CommonTabResponsListener = " + p1?.headers() + " body = " + jsonResponse)
        val resultList = ArrayList<AlbumBean>()
        val jsonArray = JSONArray(jsonResponse)
        val length = jsonArray.length()
        for (i in 0..length - 1) {
            val classifyJson = jsonArray.getJSONObject(i)
            val albumUrl = classifyJson.getString("albumUrl")
            val thumbnailUrl = classifyJson.getString("thumbnailUrl")
            val title = classifyJson.getString("title")
            resultList.add(AlbumBean(albumUrl, title, thumbnailUrl))
        }
        return resultList

    }

    override fun onError() {
        LogUtil.e("CommonTabResponsListener", "error")
        onAnalyzeError()

    }

    abstract fun onAnalyzeComplete(result: ArrayList<AlbumBean>)
    abstract fun onAnalyzeError()
}