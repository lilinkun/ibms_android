package com.hncr.ibms.ui.suggestion

import com.hncr.ibms.base.BaseViewModel
import com.hncr.ibms.http.base.NetResult
import com.hncr.ibms.http.data.SuggestionBean
import com.hncr.ibms.tools.EVENTBUS_ADDSUGGESTION_SUCCESS
import com.hncr.ibms.tools.EVENTBUS_FLOOR_DATA
import com.hncr.ibms.tools.EVENTBUS_TOAST_STRING
import com.hncr.ibms.ui.floornavigation.FloorNavigationRepository
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * Created by LG
 * on 2023/8/7  12:31
 * Description：
 */
class SuggestionViewModel(private val repository: SuggestionRepository) : BaseViewModel(){

     fun addSuggestion(content : SuggestionBean){
        launch {
            val result = repository.addSuggestion(content)
            if (result is NetResult.Success) {
                LiveEventBus.get(EVENTBUS_ADDSUGGESTION_SUCCESS,Boolean::class.java).post(true)

            }
        }
    }

}