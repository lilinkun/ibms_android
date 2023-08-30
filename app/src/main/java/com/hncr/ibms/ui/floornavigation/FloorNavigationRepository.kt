package com.hncr.ibms.ui.floornavigation

import com.hncr.ibms.http.base.BaseRepository
import com.hncr.ibms.http.base.NetResult
import com.hncr.ibms.http.data.FloorItem
import com.hncr.ibms.http.data.UserInfoBean
import com.hncr.ibms.http.data.UserItemBean
import com.hncr.ibms.http.di.FloorApi

/**
 * Created by LG
 * on 2023/8/1  17:40
 * Description：
 */
class FloorNavigationRepository(private val api : FloorApi) : BaseRepository() {

    suspend fun getFloorData():NetResult<List<FloorItem>>{
        return callRequest(call = {
            handleResponse(
                api.getFloorData()
            )
        })
    }

    suspend fun getUserList(floor : String) : NetResult<List<UserItemBean>>{
        return callRequest(call =  {
            handleResponse(
                api.getTreeInfo(floor)
            )
        } )
    }

    suspend fun searchUser(name : String) : NetResult<List<UserInfoBean>>{
        return callRequest(call =  {
            handleResponse(
                api.searchUser(name)
            )
        } )
    }

}