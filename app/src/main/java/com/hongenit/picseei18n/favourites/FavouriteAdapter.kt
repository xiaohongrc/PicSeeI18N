package com.hongenit.picseei18n.favourites

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.hongenit.picseei18n.detail.PicBean

/**
 * Created by Xiaohong on 2018/5/9.
 * desc:
 */
class FavouriteAdapter(context: Context) : PagerAdapter() {
    private var mContext: Context

    init {
        mContext = context
    }

    val favouritePhotos: ArrayList<PicBean> = arrayListOf()

    fun setData(arrayList: ArrayList<PicBean>) {
        favouritePhotos.clear()
        favouritePhotos.addAll(arrayList)
        notifyDataSetChanged()
    }


    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        val size = favouritePhotos.size
        println("size = " + size)
        return size
    }


    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        if (position < favouritePhotos.size) {
            val favouritePhotoView = FavouritePhotoView(mContext)
            val picBean = favouritePhotos[position]
            favouritePhotoView.setPic(picBean.url)
            println("picBean = " + picBean)
            favouritePhotoView.tag = picBean
            container?.addView(favouritePhotoView)
            return favouritePhotoView
        }
        return super.instantiateItem(container, position)
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        //注意：此处position是ViewPager中所有要显示的页面的position，与Adapter mDrawableResIdList并不是一一对应的。
        //因为mDrawableResIdList有可能被修改删除某一个item，在调用notifyDataSetChanged()的时候，ViewPager中的页面
        //数量并没有改变，只有当ViewPager遍历完自己所有的页面，并将不存在的页面删除后，二者才能对应起来
        if (`object` != null) {
            val viewPager = container as ViewGroup
            val count = viewPager.childCount
            for (i in 0 until count) {
                val childView = viewPager.getChildAt(i)
                if (childView === `object`) {
                    viewPager.removeView(childView)
                    break
                }
            }
        }

    }


    override fun getItemPosition(`object`: Any?): Int {
        val picBean: PicBean = ((`object` as FavouritePhotoView).tag) as PicBean
        return favouritePhotos.indexOf(picBean)

    }


}