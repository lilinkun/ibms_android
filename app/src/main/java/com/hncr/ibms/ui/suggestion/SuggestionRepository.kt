package com.hncr.ibms.ui.suggestion

import com.hncr.ibms.http.base.BaseRepository
import com.hncr.ibms.http.base.NetResult
import com.hncr.ibms.http.data.FloorItem
import com.hncr.ibms.http.data.SuggestionBean
import com.hncr.ibms.http.di.SuggestionApi
import com.hncr.ibms.ui.floornavigation.FloorNavigationRepository

/**
 * Created by LG
 * on 2023/8/7  12:36
 * Description：
 */
class SuggestionRepository(private val api : SuggestionApi) : BaseRepository() {

    suspend fun addSuggestion(content : SuggestionBean): NetResult<String> {
        return callRequest(call = {
            handleResponse(
                api.addSuggestion(content)
            )
        })
    }
}