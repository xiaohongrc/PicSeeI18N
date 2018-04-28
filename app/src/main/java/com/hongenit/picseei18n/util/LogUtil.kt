package com.hongenit.picseei18n.util

import android.util.Log

/**
 * Created by hongenit on 18/1/29.
 * log util class
 */
object LogUtil {

    private const val willLog = true

    fun temp(message: String){
        if(willLog){
            Log.i("LOG------TEMP:",message)
        }

    }

    fun i(tag: String, message: String) {
        if (willLog) {
            Log.i(tag, message)
        }

    }

    fun v(tag: String, message: String) {
        if (willLog) {
            Log.v(tag, message)
        }

    }

    fun d(tag: String, message: String) {
        if (willLog) {
            Log.d(tag, message)
        }

    }

    fun w(tag: String, message: String) {
        if (willLog) {
            Log.w(tag, message)
        }

    }

    fun e(tag: String, message: String) {
        if (willLog) {
            Log.e(tag, message)
        }

    }


}

