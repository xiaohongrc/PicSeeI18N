package com.hongenit.picseei18n.picClassify.commontab

import com.hongenit.picseei18n.detail.PicBean
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
    override fun onComplete(jsonString: String?) {
        println("onComplete currenttime = " + System.currentTimeMillis())
        onAnalyzeComplete(AnalyzeResposeData(jsonString))
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
//    }],
//    "albumIntro": "蔡徐坤帅气图片高清手机壁纸。分享一组壁纸尺寸：690*1227。蔡徐坤（August），1998年8月2日出生于湖南省，中国内地男歌手、演员。更多蔡徐坤壁纸图片尽在美桌网。",
//    "width": "640",
//    "title": "蔡徐坤帅气图片高清手机壁纸",
//    "height": "1136",
//    "whichClassify": "明星",
//    "thumbnailUrl ": "http://pic1.win4000.com/mobile/2018-04-04/5ac478a441168_250_350.jpg"

    private fun AnalyzeResposeData(jsonString: String?): ArrayList<AlbumBean> {
        println("AnalyzeResposeData11111 currenttime = " + System.currentTimeMillis())
        val resultList = ArrayList<AlbumBean>()
        val jsonArray = JSONArray(jsonString)
        val length = jsonArray.length()
        for (i in 0..length - 1) {
            val classifyJson = jsonArray.getJSONObject(i)
            val albumUrl = classifyJson.getString("albumUrl")
            val thumbnailUrl = classifyJson.getString("thumbnailUrl")
            val title = classifyJson.getString("title")
            val albumBean = AlbumBean(albumUrl, title, thumbnailUrl)

            val photosJsonArray = classifyJson.getJSONArray("albumPhotos")
            for (j in 0..photosJsonArray.length() - 1) {
                val photoJsonObject = photosJsonArray.getJSONObject(j)
                val photoUrl = photoJsonObject.getString("photoUrl")
                albumBean.albumPhotoList.add(PicBean(photoUrl, 0))
            }

            resultList.add(albumBean)
        }

        println("analyzeComplete() currenttime = " + System.currentTimeMillis())
        return resultList

    }

    override fun onError() {
        LogUtil.e("CommonTabResponsListener", "error")
        onAnalyzeError()

    }

    abstract fun onAnalyzeComplete(result: ArrayList<AlbumBean>)
    abstract fun onAnalyzeError()
}