package com.hncr.ibms.http.exception

/**
 * Created by LG
 * on 2023/8/1  17:43
 * Description：
 */
class ResultException(var errCode: String?, var msg: String?, var data: Any? = null) : Exception(msg)