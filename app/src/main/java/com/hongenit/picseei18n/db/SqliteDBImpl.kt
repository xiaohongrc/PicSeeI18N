package com.hongenit.picseei18n.db

import android.content.ContentValues
import android.content.Context
import com.hongenit.picseei18n.detail.PicBean
import com.hongenit.picseei18n.util.LogUtil

class SqliteDBImpl(context: Context) : ISqliteDB {
    val mDbHelper: DatabaseOpenHelper = DatabaseOpenHelper(context)
    val TAG = "SqliteDBImpl"

    override fun insertFavouritePic(picBean: PicBean) {
        val database = mDbHelper.writableDatabase
        database.beginTransaction()
        try {
            val contentValues = ContentValues()
            contentValues.put(DbSettings.FavouriteTable.PHOTO_URL, picBean.url)
            contentValues.put(DbSettings.FavouriteTable.FAVOURITE_TIME, picBean.favouriteTime)
            database.insert(DbSettings.FavouriteTable.TABLE_NAME, null, contentValues)
            database.setTransactionSuccessful()
            LogUtil.i(TAG, "insert success pic = " + picBean)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            database.endTransaction()
            database.close()
        }
    }

    override fun queryAllFavouritePics(): ArrayList<PicBean> {
        val picList = arrayListOf<PicBean>()
        val db = mDbHelper.writableDatabase
        val cursor = db.query(DbSettings.FavouriteTable.TABLE_NAME, null, null, null, null, null, DbSettings.FavouriteTable.FAVOURITE_TIME + " DESC")
        try {
            if (cursor == null) {
                return picList
            }
            val photoUrlIndex = cursor.getColumnIndex(DbSettings.FavouriteTable.PHOTO_URL)
            val favourTimeIndex = cursor.getColumnIndex(DbSettings.FavouriteTable.FAVOURITE_TIME)
            while (cursor!!.moveToNext()) {
                val photoUrl = cursor.getString(photoUrlIndex)
                val favourTime = cursor.getString(favourTimeIndex)
                picList.add(PicBean(photoUrl, favourTime.toLong()))
            }
        } finally {
            if (cursor != null) {
                cursor.close()
            }
            db.close()
        }

        return picList

    }


}