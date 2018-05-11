package com.hongenit.picseei18n.picClassify.commontab

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.view.ViewGroup
import com.hongenit.picseei18n.R
import com.hongenit.picseei18n.picClassify.AlbumBean
import com.hongenit.picseei18n.picClassify.BaseFragment
import com.hongenit.picseei18n.util.EventUtil
import com.hongenit.picseei18n.util.ImageLoadUtil
import kotlinx.android.synthetic.main.fragment_common_tab.*
import kotlinx.android.synthetic.main.item_common_main.view.*
import com.hongenit.picseei18n.detail.DetailsActivity
import com.hongenit.picseei18n.net.RequestUrl
import java.util.*

/**
 * Created by hongenit on 18/1/30.
 * 展示分类tab的通用fragment
 */

// url
const val KEY_ARGUMENTS_PHOTOS: String = "KEY_ARGUMENTS_URL"

const val KEY_ARGUMENTS_URL_TYPE: String = "KEY_ARGUMENTS_URL_TYPE"

class CommonTabFragment : BaseFragment() {

    var mPicList: ArrayList<AlbumBean> = arrayListOf()

    companion object {
        fun getInstance(title: String): CommonTabFragment? {
            val fragment = CommonTabFragment()
            val bundle = Bundle()
            bundle.putString(KEY_ARGUMENTS_PHOTOS, title)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getFragmentContentId(): Int {
        return R.layout.fragment_common_tab
    }

    private lateinit var mPresenter: ICommonTabPresenter

    override fun initView() {
        rvList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvList.adapter = HomeAdapter()

        rvList.addItemDecoration(GridItemDecoration(context.resources.getDimension(R.dimen.common_item_space).toInt()))
        mPresenter = CommonTabPresenter(context)
        mPresenter.setView(this)
        //刷新
        srlLayout.setColorSchemeColors(resources.getColor(R.color.colorPrimary))
        srlLayout.setOnRefreshListener {
            mPresenter.requestData(false, mUrl)
        }

        rvList.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val linearLayoutManager = recyclerView?.layoutManager as StaggeredGridLayoutManager
                    val lastPosition = linearLayoutManager.findLastVisibleItemPositions(null)
                    var isLast = false

                    for (last in lastPosition) {
                        isLast = last >= mPicList.size - 2
                        if (isLast) break
                    }
                    if (isLast) {
                        mPresenter.requestData(true, mUrl)
                    }
                }
            }

        })

    }


    override fun initData() {
        srlLayout.isRefreshing = true
        mPresenter.start(mUrl)
        println("mUrl = " + mUrl)
    }


    private lateinit var mUrl: String

    override fun initParams() {
        mUrl = RequestUrl.ALBUM_INFO_LIST + "?whichClassify=" + arguments.getString(KEY_ARGUMENTS_PHOTOS)
    }


    inner class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {
        override fun onClick(v: View?) {
            val pos = v?.getTag() as Int
            forwardDetailActivity(pos)
        }

        private fun forwardDetailActivity(pos: Int) {
            val albumBean = mPicList[pos]
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(KEY_ARGUMENTS_PHOTOS, albumBean.albumPhotoList)
            EventUtil.tab_url_click(mUrl)
            EventUtil.detail_url_click(albumBean.albumUrl)
            context.startActivity(intent)
        }

        val heightSpace = context.resources.getDimension(R.dimen.item_height_space).toInt()
        val minHeight = context.resources.getDimension(R.dimen.item_min_height).toInt()

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            val view = layoutInflater.inflate(R.layout.item_common_main, parent, false)
            return HomeViewHolder(view)
        }

        override fun getItemCount(): Int {
            return mPicList.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            val homeViewHolder = holder as HomeViewHolder
            homeViewHolder.itemView.setTag(position)
            homeViewHolder.itemView.setOnClickListener(this)
            val layoutParams = homeViewHolder.itemView.layoutParams
            layoutParams.height = (minHeight + Math.random() * heightSpace).toInt()
            homeViewHolder.itemView.layoutParams = layoutParams
            var picBean = mPicList[position]
            ImageLoadUtil.newInstance()?.loadImage(context, homeViewHolder.itemView.ivGirlImage, picBean.thumbnailUrl)
            homeViewHolder.itemView.tvImageTitle.text = picBean.title
        }

        inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    }

    // 刷新数据
    fun replaceData(picList: ArrayList<AlbumBean>) {
        mPicList.clear()
        mPicList.addAll(picList)
        if (isVisible) {
            rvList.adapter.notifyDataSetChanged()
            srlLayout.isRefreshing = false
        }

    }

    // 加载更多时添加数据
    fun addData(picList: ArrayList<AlbumBean>) {
        if (picList.size > 0) {
            mPicList.addAll(picList)
            rvList.adapter.notifyDataSetChanged()
        }
        srlLayout.isRefreshing = false
    }


}