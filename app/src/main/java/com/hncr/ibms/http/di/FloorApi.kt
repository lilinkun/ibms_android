package com.hncr.ibms.http.di

import com.hncr.ibms.http.Config.SERVER_BASE_URL
import com.hncr.ibms.http.base.BaseModel
import com.hncr.ibms.http.data.FloorItem
import com.hncr.ibms.http.data.UserInfoBean
import com.hncr.ibms.http.data.UserItemBean
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by LG
 * on 2023/8/1  17:23
 * Description：
 */
interface FloorApi {

    /**
     * 获取楼层
     */
    @GET("externalScreen/floor")
    suspend fun getFloorData() : BaseModel<List<FloorItem>>

    /**
     * 获取楼层人信息
     */
    @GET("externalScreen/treeInfo")
    suspend fun getTreeInfo(
        @Query("floor") floor : String
        ) : BaseModel<List<UserItemBean>>

    /**
     * 查询人信息
     */
    @GET("externalScreen/search")
    suspend fun searchUser(
        @Query("name") name : String
    ) : BaseModel<List<UserInfoBean>>
}