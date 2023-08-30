package com.hncr.ibms.http.base

/**
 * Created by LG
 * on 2023/8/1  17:25
 * Description：
 */
data class BaseModel<out T>(val code:Int,val msg:String,val data:T?)
