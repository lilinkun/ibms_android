package com.hncr.ibms.ui.floornavigation

import com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
import com.hncr.ibms.base.BaseViewModel
import com.hncr.ibms.http.base.NetResult
import com.hncr.ibms.http.data.FloorItem
import com.hncr.ibms.http.data.UserInfoBean
import com.hncr.ibms.http.data.UserItemBean
import com.hncr.ibms.tools.EVENTBUS_FLOOR_DATA
import com.hncr.ibms.tools.EVENTBUS_FLOOR_USER
import com.hncr.ibms.tools.EVENTBUS_SEARCH_USER
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * Created by LG
 * on 2023/8/2  9:54
 * Description：
 */
class FloorNagationViewModel(private val repository: FloorNavigationRepository) : BaseViewModel() {

    var floorList = listOf<FloorItem>()

    var treeInfo = listOf<UserItemBean>()

    var userList = listOf<UserInfoBean>()

    fun init(){
        launch {
            getFloorInfo()
        }
    }

    private suspend fun getFloorInfo(){
            val result = repository.getFloorData()
            if (result is NetResult.Success){
                result.data?.let {
                    floorList = it

                    LiveEventBus.get(EVENTBUS_FLOOR_DATA,Boolean::class.java).post(true)

                }
            }
    }

    fun getUserList(floor : String){
        launch {
            val result = repository.getUserList(floor)
            if (result is NetResult.Success){
                result.data?.let {
                    treeInfo = it

                    LiveEventBus.get(EVENTBUS_FLOOR_USER,Boolean::class.java).post(true)

                }
            }
        }
    }

    fun searchUser(name : String){
        launch {
            val result = repository.searchUser(name)
            if (result is NetResult.Success){
                result.data?.let {
                    userList = it

                    LiveEventBus.get(EVENTBUS_SEARCH_USER,Boolean::class.java).post(true)

                }
            }
        }
    }

}