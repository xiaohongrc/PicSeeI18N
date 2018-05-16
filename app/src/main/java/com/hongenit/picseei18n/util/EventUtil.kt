package com.hongenit.picseei18n.util

import android.os.Bundle
import com.hongenit.picseei18n.PicSeeApplication

/**
 * Created by hongenit on 18/2/8.
 * umeng事件
 */
object EventUtil {
    fun onEvent(action: String, bundle: Bundle? = null) {
        PicSeeApplication.getFirebaseEventLogger().logEvent(action, bundle)

    }

    // drawer_open
    fun drawer_action(bundle: Bundle?) {
        onEvent("drawer_action", bundle)
    }


    // drawer_open_btn_click
    fun drawer_open_btn_click() {
        onEvent("drawer_open_btn_click")
    }


    // drawer_close_btn_click
    fun drawer_close_btn_click() {
        onEvent("drawer_close_btn_click")
    }


    //  menu_click_favorites
    fun menu_click_favorites() {
        onEvent("menu_click_favorites")
    }


    // menu_click_feedback
    fun menu_click_feedback() {
        onEvent("menu_click_feedback")
    }

    // menu_click_setting
    fun menu_click_setting() {
        onEvent("menu_click_setting")
    }

    // request_classify
    fun request_classify() {
        onEvent("request_classify")
    }

    // album_to_detail_click
    fun album_to_detail_click(bundle: Bundle?) {
        onEvent("album_to_detail_click", bundle)
    }

    // detail_photo_click
    fun detail_photo_click(bundle: Bundle?) {
        onEvent("detail_photo_click",bundle)
    }



/*
    //
    fun() {
        onEvent("")
    }

    //
    fun() {
        onEvent("")
    }

    //
    fun() {
        onEvent("")
    }

    //
    fun() {
        onEvent("")
    }

    //
    fun() {
        onEvent("")
    }
*/

    object FirebaseEventParams {

        val drawer_close = "drawer_close"
        val drawer_open = "drawer_open"
        val urlWithParam = "urlWithParam"
        val albumUrl: String? = "albumUrl"
        val tabUrl: String? = "tabUrl"
        val thumbnailUrl: String? = "thumbnailUrl"

    }


}