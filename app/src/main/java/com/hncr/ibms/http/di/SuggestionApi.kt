package com.hncr.ibms.http.di

import com.hncr.ibms.http.base.BaseModel
import com.hncr.ibms.http.data.FloorItem
import com.hncr.ibms.http.data.SuggestionBean
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by LG
 * on 2023/8/7  12:37
 * Description：
 */
interface SuggestionApi{


    /**
     * 提交意见
     */
    @POST("suggestion/addSuggestion")
    suspend fun addSuggestion(
        @Body content: SuggestionBean
    ) : BaseModel<String>

}