package com.hncr.ibms.http.exception


/**
 * Created by LG
 * on 2023/8/1  17:45
 * Description：
 */
object DealException {

    fun handlerException(t: Throwable): ResultException {
        val ex: ResultException
        if (t is ResultException) {
            ex = t
        } else {
            ex = ResultException(
                "1000",
                "未知错误"
            )
        }
        return ex
    }
}