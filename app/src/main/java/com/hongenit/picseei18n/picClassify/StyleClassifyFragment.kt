package com.hongenit.picseei18n.picClassify

//import android.support.design.widget.TabLayout
import android.support.design.widget.TabLayout
import com.hongenit.picseei18n.R
import kotlinx.android.synthetic.main.fragment_area_classify.*

/**
 * Created by hongenit on 18/1/30.
 * 风格分类fragment
 */
class StyleClassifyFragment : BaseFragment() {

    companion object {
        fun newInstance(): StyleClassifyFragment {
            val fragment = StyleClassifyFragment()
            return fragment
        }
    }

    override fun getFragmentContentId(): Int {
        return R.layout.fragment_area_classify
    }

    private lateinit var mvpAdapter: AreaVpAdapter

    override fun initView() {

        mvpAdapter = AreaVpAdapter(context,childFragmentManager)
        vpArea.adapter = mvpAdapter
        tabLayout.setupWithViewPager(vpArea)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
    }

//    private lateinit var mPresenter: ClassifyPresenter

    override fun initData() {
//        mPresenter = ClassifyPresenter(this)
//        mPresenter.requestClassifyData()


    }

//    fun RefreshData(result: ArrayList<ClassifyTypeBean>) {
//        mvpAdapter.setData(result)
//        for (item in result){
//            println(item)
//
//        }
//        mvpAdapter.notifyDataSetChanged()
//
//    }



}