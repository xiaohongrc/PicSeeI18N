package com.hongenit.picseei18n.detail

import com.hongenit.picseei18n.picClassify.commontab.WEBSITE_PREFIX_MSGAO
import com.hongenit.picseei18n.picClassify.commontab.WEBSITE_PREFIX_WIN4000

/**
 * Created by hongenit on 18/2/2.
 * 请求该图片详情的数据
 */
private val TAG: String = "DetailModel"

class DetailModel {

    companion object {
        fun requestDetails(url: String, index: Int, response: DetailResponse) {

            when (getUrlPrefix(url)) {
                WEBSITE_PREFIX_MSGAO -> requestDetailMsgao(url, response)
                WEBSITE_PREFIX_WIN4000 -> requestDetailWin4000(url, response)
                // 有可能出现http://zgnd.msgao.com的前缀
                else -> requestDetailMsgao(url, response)
            }

        }


        /**  从www.win4000.com
         * eg. http://www.win4000.com/mobile_detail_142821.html
         */
        private fun requestDetailWin4000(url: String, response: DetailResponse) {
            /*
           <div class="scroll-img-cont scroll-img-cont02">
                <ul id="scroll" class="scroll-img scroll-img02 clearfix">
                    <li  class="current"  >
                <a href="http://www.win4000.com/mobile_detail_143195.html">
                    <img src="http://static.win4000.com/home/images/placeholder.jpg"
                     data-original="http://pic1.win4000.com/mobile/2018-02-05/5a7814b935bec_130_170.jpg"
                        alt="矢量插画汽车手机壁纸图片" title="矢量插画汽车手机壁纸图片"/>
                 </a>
             */

        }


        /**
         *
         *  根据缩略图地址得到大图地址。
         * eg.  http://pic1.win4000.com/mobile/2018-02-05/5a7814b935bec_130_170.jpg
         *          http://pic1.win4000.com/mobile/2018-02-05/5a7814b935bec.jpg
         *
         */
        private fun getImgUrlByThumbnail(thumbnailsUrl: String): String? {
            var imgUrl: String? = null
            val start: Int = thumbnailsUrl.indexOf("_")
            val end: Int = thumbnailsUrl.indexOf(".jpg", 0, true)
            if (start != -1 && end != -1) {
                imgUrl = thumbnailsUrl.replaceRange(start, end, "")
            }
            return imgUrl
        }


        //  从msgao.com获取数据
        // eg. http://www.msgao.com/wmtp/gqbz/546028.shtml
        private fun requestDetailMsgao(url: String, response: DetailResponse) {
        }


        private fun getUrlPrefix(url: String): String {
            val indexOf = url.indexOf(".com/", 0, true)
            var urlPrefix = url.substring(0, indexOf)
            return urlPrefix
        }

    }


}