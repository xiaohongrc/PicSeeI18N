package com.hongenit.picseei18n

import android.content.Context
import com.hongenit.picseei18n.db.ISqliteDB
import com.hongenit.picseei18n.db.SqliteDBImpl
import com.hongenit.picseei18n.detail.PicBean

/**
 * Created by Xiaohong on 2018/5/11.
 * desc:
 */
class DataModel {
    var mSqliteDB: ISqliteDB? = null
    var mFavouritePics: ArrayList<PicBean> = arrayListOf()

    companion object {
        var mInstance: DataModel? = null
        fun getInstance(): DataModel {
            if (mInstance == null) {
                mInstance = DataModel()
            }
            return mInstance!!
        }
    }


    private var mContext: Context? = null

    fun initDataModel(context: Context) {
        mContext = context
        mSqliteDB = SqliteDBImpl(context)
    }

    fun queryAllFavouritePics() {
        mFavouritePics = mSqliteDB?.queryAllFavouritePics()!!
        notifyFavouriteListChanged()
    }

    interface FavouriteListChangedListener {
        fun onFavouriteListChanged()
    }

    var mListChangedListeners = arrayListOf<FavouriteListChangedListener>()
    fun addFavouriteListChangedListener(listener: FavouriteListChangedListener) {
        mListChangedListeners.add(listener)
    }

    fun removeFavouriteListChangedListener(listener: FavouriteListChangedListener) {
        mListChangedListeners.remove(listener)
    }

    private fun notifyFavouriteListChanged() {
        mListChangedListeners.forEach {
            it.onFavouriteListChanged()
        }

    }

    // 插入喜欢的图片
    fun insertFavouritePic(picBean: PicBean) {
        mSqliteDB?.insertFavouritePic(picBean)
        queryAllFavouritePics()
    }


}