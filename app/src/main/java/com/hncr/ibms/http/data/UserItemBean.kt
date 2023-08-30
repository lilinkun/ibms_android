package com.hncr.ibms.http.data

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * Created by LG
 * on 2023/8/2  19:46
 * Description：
 */
data class UserItemBean(val id : Int, val label : String, val roomId : String, val level : Int, val children : List<UserItemBean>)
