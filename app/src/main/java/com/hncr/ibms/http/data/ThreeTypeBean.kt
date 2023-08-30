package com.hncr.ibms.http.data

import com.chad.library.adapter.base.entity.AbstractExpandableItem
import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * Created by LG
 * on 2023/8/2  19:38
 * Description：
 */
data class ThreeTypeBean(val id : Int, val label : String, val roomId : String?) : MultiItemEntity {

    override fun getItemType(): Int {
        return 2
    }
}