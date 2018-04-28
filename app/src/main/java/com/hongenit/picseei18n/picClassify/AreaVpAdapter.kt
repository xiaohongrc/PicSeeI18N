package com.hongenit.picseei18n.picClassify

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hongenit.picseei18n.picClassify.commontab.CommonTabFragment

/**
 * Created by hongenit on 18/1/30.
 * 分类适配器
 */

class AreaVpAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var mFragmentDataList: ArrayList<ClassifyTypeBean> = ArrayList<ClassifyTypeBean>()

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