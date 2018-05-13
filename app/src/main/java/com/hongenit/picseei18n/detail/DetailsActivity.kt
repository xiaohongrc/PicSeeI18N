package com.hongenit.picseei18n.detail

import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import com.hongenit.picseei18n.BaseActivity
import com.hongenit.picseei18n.R
import com.hongenit.picseei18n.picClassify.commontab.KEY_ARGUMENTS_PHOTOS
import gallerylibrary.CardAdapterHelper
import gallerylibrary.CardScaleHelper
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_image_details.view.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.Window
import android.view.WindowManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.hongenit.picseei18n.Constants
import com.hongenit.picseei18n.DataModel
import com.hongenit.picseei18n.db.SqliteDBImpl
import com.hongenit.picseei18n.util.*
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream


/**
 * @author Xiaohong
 * create at 2018/2/1 16:22
 * description：
 */
class DetailsActivity : BaseActivity(), DataModel.FavouriteListChangedListener {
    override fun onFavouriteListChanged() {
//        speedRecyclerView.adapter.notifyDataSetChanged()
    }

    val TAG = "DetailsActivity"
    var mPicList = arrayListOf<PicBean>()

//    fun replaceData(picList: ArrayList<PicBean>) {
//        mPicList.clear()
//        mPicList.addAll(picList)
//        speedRecyclerView.adapter.notifyDataSetChanged()
//    }

    //    private var mUrl= ArrayList<String>()
    fun initParams() {
        mPicList = intent.getSerializableExtra(KEY_ARGUMENTS_PHOTOS) as ArrayList<PicBean>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*set it to be no title*/
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        /*set it to be full screen*/
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_details)
        initParams()
        initView()
        initData()

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                println("onAdLoaded")

                super.onAdLoaded()
            }

            override fun onAdClosed() {
                println("onAdClosed")

                super.onAdClosed()
            }

            override fun onAdClicked() {
                println("onAdClicked")

                super.onAdClicked()
            }

            override fun onAdFailedToLoad(p0: Int) {
                println("onAdFailedToLoad code = " + p0)
                super.onAdFailedToLoad(p0)
            }

        }

    }

    private lateinit var mCardScaleHelper: CardScaleHelper

    private lateinit var mPresenter: IDetailPresenter

    fun initView() {
        mPresenter = DetailPresenter()

//        setToolBar("详情")
        speedRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        speedRecyclerView.adapter = DetailsListAdapter()

        mCardScaleHelper = CardScaleHelper()
        mCardScaleHelper.setCurrentItemPos(0)
        mCardScaleHelper.attachToRecyclerView(speedRecyclerView)
        speedRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> loadingView.alpha = 1f
                    RecyclerView.SCROLL_STATE_DRAGGING -> loadingView.alpha = 0f
                    RecyclerView.SCROLL_STATE_SETTLING -> loadingView.alpha = 0f
                }
            }
        })

        DataModel.getInstance().addFavouriteListChangedListener(this)

    }

    fun initData() {

    }

    override fun onResume() {
        super.onResume()
        mPresenter.start(this)

    }

    fun showLoadingView(show: Boolean) {
        loadingView.visibility = if (show) View.VISIBLE else View.GONE
    }

//    private fun initBlurBackground() {
//        speedRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    notifyBackgroundChange()
//                }
//            }
//        })
//
//        notifyBackgroundChange()
//    }

    private var mBlurRunnable: Runnable? = null

    private var mLastPos: Int = 0

//    private fun notifyBackgroundChange() {
//        if (mLastPos == mCardScaleHelper.getCurrentItemPos()) return
//        mLastPos = mCardScaleHelper.getCurrentItemPos()
//        val imageUrl = mPicList.get(mCardScaleHelper.getCurrentItemPos()).url
//        blurView.removeCallbacks(mBlurRunnable)
//        doAsync {
//            mBlurRunnable = Runnable {
//                val bitmap = getbitmap(imageUrl)
//                println(bitmap)
//                uiThread {
//                    //                    ViewSwitchUtils.startSwitchBackgroundAnim(blurView, BlurBitmapUtils.getBlurBitmap(blurView.getContext(), bitmap, 15))
//                    blurView.postDelayed(mBlurRunnable, 200)
//                }
//            }
//        }
//    }


    /**
     * 根据一个网络连接(String)获取bitmap图像
     *
     * @param imageUri
     * @return
     * @throws MalformedURLException
     */
    fun getbitmap(imageUri: String): Bitmap? {
        // 显示网络上的图片
        var bitmap: Bitmap?
        try {
            val myFileUrl = URL(imageUri)
            val conn = myFileUrl
                    .openConnection() as HttpURLConnection
            conn.setDoInput(true)
            conn.connect()
            val inputStream = conn.getInputStream()
            bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()

        } catch (e: Error) {
            e.printStackTrace()
            bitmap = null
        } catch (e: IOException) {
            e.printStackTrace()
            bitmap = null
        }
        return bitmap
    }


    private val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: Int = 1000

    inner class DetailsListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private val mCardAdapterHelper = CardAdapterHelper()

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            val itemView = layoutInflater.inflate(R.layout.item_image_details, parent, false)
//            mCardAdapterHelper.setPagePadding(100)
            mCardAdapterHelper.onCreateViewHolder(parent, itemView)
            return DetailViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            val detailViewHolder = holder as DetailViewHolder
            // 如果已经点赞则隐藏按钮
            val hadFavour = DataModel.getInstance().mFavouritePics.contains(mPicList[position])
            if (hadFavour) {
                detailViewHolder.itemView.bt_favour.visibility = View.GONE
            }

            detailViewHolder.itemView.setOnClickListener(View.OnClickListener {
                detailViewHolder.itemView.bt_download.visibility = if (detailViewHolder.itemView.bt_download.isShown) View.GONE else View.VISIBLE
                val hadFavour_click = DataModel.getInstance().mFavouritePics.contains(mPicList[position])
                detailViewHolder.itemView.bt_favour.visibility = if (detailViewHolder.itemView.bt_favour.isShown || hadFavour_click) View.GONE else View.VISIBLE
            })
            detailViewHolder.itemView.bt_download.setOnClickListener(View.OnClickListener {
                var bitmapDrawable: BitmapDrawable? = null
                try {
                    bitmapDrawable = detailViewHolder.itemView.ivDetailPic.drawable as BitmapDrawable
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                if (bitmapDrawable != null) {
                    downLoadPic(bitmapDrawable.bitmap, position)
                }

            })

            detailViewHolder.itemView.bt_favour.setOnClickListener(View.OnClickListener {
                doFavourPic(position)
                it.visibility = View.GONE
            })
            mCardAdapterHelper.onBindViewHolder(detailViewHolder.itemView, position, mPicList.size)
            val cornerRadius = resources.getDimension(R.dimen.detail_cardview_radius)
            ImageLoadUtil.newInstance()!!.loadRoundImage(this@DetailsActivity, detailViewHolder.itemView.ivDetailPic, mPicList[position].url, cornerRadius, object : ImageLoadUtil.ImageLoadListener {
                override fun onLoadComplete() {
                    detailViewHolder.itemView.loadingView.visibility = View.GONE
                }

                override fun onLoadFailed() {
                    detailViewHolder.itemView.tvLoadImageFail.visibility = View.VISIBLE
                    detailViewHolder.itemView.loadingView.visibility = View.GONE
                }
            })

        }

        // 收藏照片
        private fun doFavourPic(position: Int) {
            if (position < mPicList.size) {
                val picUrl = mPicList[position].url
                val picBean = PicBean(picUrl, System.currentTimeMillis())
                DataModel.getInstance().insertFavouritePic(picBean)
            }

        }


        // 下载图片
        private fun downLoadPic(bitmap: Bitmap?, position: Int) {
            if (ContextCompat.checkSelfPermission(this@DetailsActivity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this@DetailsActivity,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this@DetailsActivity,
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)

                    // MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {


                val externalStorageState = Environment.getExternalStorageState()
                if (externalStorageState == Environment.MEDIA_MOUNTED) {
                    val imageCacheDir = File(Environment.getExternalStorageDirectory(), "picseesee")
                    val fileName = Utils.getMD5(mPicList[position].url) + ".jpeg"
                    try {
//                    var file: File? = null
                        if (!imageCacheDir.exists()) {
                            imageCacheDir.mkdirs()
                        }
                        val file = File(imageCacheDir, fileName)
                        if (file == null) {
                            LogUtil.e(TAG, "创建图片文件失败")
                            return
                        }
                        if (!file.createNewFile()) {
                            LogUtil.e(TAG, "文件已经存在")
                            return
                        }

                        val out = BufferedOutputStream(FileOutputStream(file))
                        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, out)
                        out.flush()
                        out.close()
                        //保存图片后发送广播通知更新数据库
                        val uri = Uri.fromFile(file)
                        LogUtil.d(TAG, "创建成功")
                        ToastUtil.showToast(getString(R.string.download_success))

                        sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
                    } catch (e: Exception) {
                        LogUtil.e(TAG, "下载图片失败")
                        e.printStackTrace()
                    }

                }

            }

        }


        override fun getItemCount(): Int {
            return mPicList.size
        }
    }

    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        DataModel.getInstance().removeFavouriteListChangedListener(this)
        super.onStop()
    }


}