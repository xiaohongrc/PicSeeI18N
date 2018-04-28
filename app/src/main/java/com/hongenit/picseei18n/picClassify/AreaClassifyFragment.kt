package com.hongenit.picseei18n.picClassify

import android.support.design.widget.TabLayout
import com.hongenit.picseei18n.R
import kotlinx.android.synthetic.main.fragment_area_classify.*


/**
 * Created by Xiaohong on 18/1/30.
 * 分类的fragment
 */

class AreaClassifyFragment : BaseFragment() {
    companion object {
        var fragment: AreaClassifyFragment? = null
        fun newInstance(): AreaClassifyFragment? {
            if (fragment == null) {
                fragment = AreaClassifyFragment()
            }
            return fragment
        }
    }

    override fun getFragmentContentId(): Int {
        return R.layout.fragment_area_classify
    }

    override fun initView() {
        vpArea.adapter = AreaVpAdapter(childFragmentManager)

        tabLayout.setupWithViewPager(vpArea)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
    }

    override fun initData() {

    }

    private fun getFragmentData(): ArrayList<ClassifyTypeBean?> {

        val classifyTypeSet = HashSet<ClassifyTypeBean?>()




//        将无序set转成有序list
        val classifyTypeList = ArrayList<ClassifyTypeBean?>()
        for (bean in classifyTypeSet) {
            classifyTypeList.add(bean)
        }
        return classifyTypeList
    }


}
