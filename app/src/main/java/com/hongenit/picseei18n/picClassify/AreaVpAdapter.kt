package com.hongenit.picseei18n.picClassify

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hongenit.picseei18n.picClassify.commontab.CommonTabFragment
import com.hongenit.picseei18n.util.LogUtil

/**
 * Created by hongenit on 18/1/30.
 * 分类适配器
 */

class AreaVpAdapter(context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    val TAG = "AreaVpAdapter"
    var mFragmentDataList: ArrayList<ClassifyTypeBean> = ArrayList<ClassifyTypeBean>()

    val titleResNames = arrayOf("classify_mingxing", "classify_meinv", "classify_fengjing", "classify_qiche", "classify_dongman", "classify_dongwu", "classify_zhiwu", "classify_tiyu", "classify_meishi")

    init {
        for (i in 0..titleResNames.size - 1) {

            val titleResName = titleResNames[i]
            val idResId = context.resources.getIdentifier(titleResName, "integer", context.packageName)
            val nameResId = context.resources.getIdentifier(titleResName, "string", context.packageName)

            val title = context.getString(nameResId)
            LogUtil.d(TAG, "idResId = " + idResId + "nameRe sId = " + nameResId);



            mFragmentDataList.add(ClassifyTypeBean("", titleResName,title))
        }
    }

    fun setData(data: ArrayList<ClassifyTypeBean>) {
        mFragmentDataList = data

    }

    override fun getItem(position: Int): Fragment? {
        return CommonTabFragment.getInstance(mFragmentDataList[position].titleResName)
    }

    override fun getCount(): Int {
        return mFragmentDataList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mFragmentDataList[position].title
    }


}