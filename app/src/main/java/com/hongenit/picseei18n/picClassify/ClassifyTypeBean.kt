package com.hongenit.picseei18n.picClassify

/**
 * Created by hongenit on 18/2/1.
 * 分类类型
 */
data class ClassifyTypeBean(var classifyUrl: String, var titleResName: String, var title: String) {
    override fun toString(): String {
        return "ClassifyTypeBean(classifyUrl='$classifyUrl',titleResName='$titleResName' title='$title')"
    }
}