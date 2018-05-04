package com.hongenit.picseei18n.picClassify

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hongenit.picseei18n.R
import com.hongenit.picseei18n.picClassify.commontab.CommonTabFragment

/**
 * Created by hongenit on 18/1/30.
 * 分类适配器
 */

class AreaVpAdapter(context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    var mFragmentDataList: ArrayList<ClassifyTypeBean> = ArrayList<ClassifyTypeBean>()

    val titleIds = intArrayOf(R.string.classify_mingxing, R.string.classify_meinv,
            R.string.classify_fengjing, R.string.classify_qiche, R.string.classify_dongman,
            R.string.classify_dongwu, R.string.classify_zhiwu, R.string.classify_tiyu,
            R.string.classify_meishi)

    init {
        for (i in 0..titleIds.size - 1) {
            val title = context.getString(titleIds[i])
            mFragmentDataList.add(ClassifyTypeBean("", title))
        }
    }

    fun setData(data: ArrayList<ClassifyTypeBean>) {
        mFragmentDataList = data

    }

    override fun getItem(position: Int): Fragment? {
        return CommonTabFragment.getInstance(mFragmentDataList[position].title)
    }

    override fun getCount(): Int {
        return mFragmentDataList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mFragmentDataList[position].title
    }


}