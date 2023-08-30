package com.hncr.ibms.http.base

import com.hncr.ibms.http.exception.ResultException

/**
 * Created by LG
 * on 2023/8/1  17:42
 * Description：
 */
sealed class NetResult<out T : Any> {

    data class Success<out T : Any>(val data: T?) : NetResult<T>()

    data class Error(val exception: ResultException) : NetResult<Nothing>()


}