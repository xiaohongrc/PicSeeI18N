package com.hongenit.picseei18n.favourites

import android.view.View
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.hongenit.picseei18n.DataModel
import com.hongenit.picseei18n.R
import com.hongenit.picseei18n.db.SqliteDBImpl
import com.hongenit.picseei18n.picClassify.BaseFragment
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.fragment_favourite.*
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by Xiaohong on 2018/5/9.
 * desc:
 */
class FavouriteFragment : BaseFragment() {
    override fun getFragmentContentId(): Int {
        return R.layout.fragment_favourite
    }

    override fun initView() {
        vp_favourites.adapter = FavouriteAdapter(context)
        initAdmob()
    }

    private fun initAdmob() {
        val adRequest = AdRequest.Builder().build()
        adView_banner2.loadAd(adRequest)
        adView_banner2.adListener = object : AdListener() {
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

    override fun initData() {
        (vp_favourites.adapter as FavouriteAdapter).setData(DataModel.getInstance().mFavouritePics)
        mEmptyView.visibility = if (vp_favourites.adapter.count > 0) View.GONE else View.VISIBLE
    }
}

/*
KotlinIO1.kt （Kotlin的IO操作1）

fun main(args: Array<String>) {
    val file = File("build.gradle") //新建文件读取build.gradle的内容
    //把文件内容读取进缓冲读取器
    val bufferedReader = BufferedReader(FileReader(file))
    var line: String

    while (true) {
        //当有内容时读取一行数据，否则退出循环
        line = bufferedReader.readLine() ?: break
        println(line) //打印一行数据
    }
    bufferedReader.close() //关闭缓冲读取器
}
KotlinIO2.kt （Kotlin的IO操作2）

fun main(args: Array<String>) {
    val file = File("build.gradle") //新建文件读取build.gradle的内容

    //把文件内容读取进缓冲读取器（use方法会自动对BufferedReader进行关闭）
    BufferedReader(FileReader(file)).use {
        var line: String
        while (true) {
            line = it.readLine() ?: break //当有内容时读取一行数据，否则退出循环
            println(line) //打印一行数据
        }
    }
}
KotlinIO3.kt（Kotlin的IO操作3，最简单的一种）

fun main(args: Array<String>) {
    //最简单打印文件内容的方法，readLines（）方法可读取文件内容
    File("build.gradle").readLines().forEach(::println)
}

作者：lkmc2
链接：https://www.jianshu.com/p/832b19b8a025
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/